app.controller("tipoequipamentoController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("tipoequipamento/recuperar/" + $routeParams.id).success(function(response) {
			$scope.tipoequipamento = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.tipoequipamento.fabricante.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo equipamento	
			$scope.tipoequipamento = {};
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("tipoequipamento/salvar", $scope.tipoequipamento).success(function(response) {
			$scope.tipoequipamento = {};
			sucesso(response);
			$location.path('listtipoequipamento/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};
	
	$scope.listarTipoequipamento = function () {
		$http.get("tipoequipamento/listar").success(function(response) {
			console.log('tipoequipamento listar - response-'+response);
			$scope.tipoequipamentos = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarTipoequipamento -'+response);
		});
	};	
	
	$scope.editartipoequipamento = function(id) {
		$location.path('tipoequipamentoEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('tipoequipamento/cadastro/');
	};
	
	$scope.excluirTipoequipamento = function (cod) {
			$scope.removerTipoequipamento = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("tipoequipamento/excluir/"+$scope.removerTipoequipamento).success(function(response) {
			sucesso("Tipo Equipamento Excluída!");
			$scope.listarTipoequipamento();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerTipoequipamento = {};
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
		$location.path('listtipoequipamento/');
	};

	$scope.sair = function () {
		$http.get("tipoequipamento/sair").success(function(response) {
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
