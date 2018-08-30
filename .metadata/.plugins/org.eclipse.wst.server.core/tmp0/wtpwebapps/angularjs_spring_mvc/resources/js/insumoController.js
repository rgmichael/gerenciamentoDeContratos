app.controller("insumoController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o insumo
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("insumo/recuperar/" + $routeParams.id).success(function(response) {
			$scope.insumo = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {
					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.insumo.fabricante.id));
					}, 1000);
					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo insumo		
			$scope.insumo = {};	
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("insumo/salvar", $scope.insumo).success(function(response) {
			$scope.insumo = {};
			sucesso(response);
			$location.path('listinsumo/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};	
	
	$scope.listarInsumos = function () {
		$http.get("insumo/listar").success(function(response) {
			console.log('insumo listar - response-'+response);
			$scope.insumos = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarInsumo -'+response);
		});
	};	
	
	$scope.editarinsumo = function(id) {
		$location.path('insumoEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('insumo/cadastro/');
	};
	
	$scope.excluirInsumo = function (cod) {
			$scope.removerInsumo = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("insumo/excluir/"+$scope.removerInsumo).success(function(response) {
			sucesso("Insumo Excluído!");
			$scope.listarInsumos();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerInsumo = {};
	};
	
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
	
	$scope.voltar = function() {
		$location.path('listinsumo/');
	};
	
	$scope.sair = function () {
		$http.get("insumo/sair").success(function(response) {
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