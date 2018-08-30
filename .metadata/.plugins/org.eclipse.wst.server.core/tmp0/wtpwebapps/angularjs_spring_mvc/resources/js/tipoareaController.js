app.controller("tipoareaController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("tipoarea/recuperar/" + $routeParams.id).success(function(response) {
			$scope.tipoarea = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.tipoarea.fabricante.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo equipamento	
			$scope.tipoarea = {};
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("tipoarea/salvar", $scope.tipoarea).success(function(response) {
			$scope.tipoarea = {};
			sucesso(response);
			$location.path('listtipoarea/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};
	
	$scope.listarTipoarea = function () {
		$http.get("tipoarea/listar").success(function(response) {
			console.log('tipoarea listar - response-'+response);
			$scope.tipoareas = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarTipoarea -'+response);
		});
	};	
	
	$scope.editartipoarea = function(id) {
		$location.path('tipoareaEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('tipoarea/cadastro/');
	};
	
	$scope.excluirTipoarea = function (cod) {
			$scope.removerTipoarea = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("tipoarea/excluir/"+$scope.removerTipoarea).success(function(response) {
			sucesso("Tipo Area Excluída!");
			$scope.listarTipoarea();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerTipoarea = {};
	};
	
	/*
	$scope.carregarCombos = function() {
		
		if ($routeParams.id == null || $routeParams.id == undefined || $routeParams.id == ''){
			
			$scope.fabricanteesList = [{}];
			
			$http.get("fabricante/listar").success(function(response) {
				console.log('fabricanteesList'+response);
				$scope.fabricanteesList = response;
			}).error(function(data, status, headers, config) {
				erro("Ocorreu um erro ao carregar a tela");
				console.log('Erro - carregarCombos -'+response);
			});
		}
	}
	*/	
	$scope.voltar = function() {
		$location.path('listtipoarea/');
	};

	$scope.sair = function () {
		$http.get("tipoarea/sair").success(function(response) {
			 $window.location.reload();
		}).error(function(response) {
			erro("Ocorreu um erro ao sair");
			console.log('Erro - sair -'+response);
		});
	};	

});

//mostra msg de erro
function erro(msg) {
	$.notify({
    	message: msg
    },{
        type: 'danger',
        timer: 1000       
    });
}

//mostra msg de sucesso
function sucesso(msg) {
 	$.notify({
     	message: msg
     },{
         type: 'success',
         timer: 1000
     });
}
