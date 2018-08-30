app.controller("contratanteController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("contratante/recuperar/" + $routeParams.id).success(function(response) {
			$scope.contratante = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.contratante.fabricante.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo equipamento	
			$scope.contratante = {};
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("contratante/salvar", $scope.contratante).success(function(response) {
			$scope.contratante = {};
			sucesso(response);
			$location.path('listcontratante/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};
	
	$scope.listarContratante = function () {
		$http.get("contratante/listar").success(function(response) {
			console.log('contratante listar - response-'+response);
			$scope.contratantes = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarContratante -'+response);
		});
	};	
	
	$scope.editarcontratante = function(id) {
		$location.path('contratanteEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('contratante/cadastro/');
	};
	
	$scope.excluirContratante = function (cod) {
			$scope.removerContratante = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("contratante/excluir/"+$scope.removerContratante).success(function(response) {
			sucesso("Contratante Excluída!");
			$scope.listarContratante();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerContratante = {};
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
		$location.path('listcontratante/');
	};

	$scope.sair = function () {
		$http.get("contratante/sair").success(function(response) {
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
