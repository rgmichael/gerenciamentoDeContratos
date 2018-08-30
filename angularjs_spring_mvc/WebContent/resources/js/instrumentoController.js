app.controller("instrumentoController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o insumo
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("instrumento/recuperar/" + $routeParams.id).success(function(response) {
			$scope.instrumento = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {
					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.instrumento.fabricante.id));
					}, 1000);
					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo instrumento		
			$scope.instrumento = {};	
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("instrumento/salvar", $scope.instrumento).success(function(response) {
			$scope.instrumento = {};
			sucesso(response);
			$location.path('listinstrumento/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};	
	
	$scope.listarInstrumentos = function () {
		$http.get("instrumento/listar").success(function(response) {
			console.log('instrumento listar - response-'+response);
			$scope.instrumentos = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarInstrumento -'+response);
		});
	};	
	
	$scope.editarinstrumento = function(id) {
		$location.path('instrumentoEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('instrumento/cadastro/');
	};
	
	$scope.excluirInstrumento = function (cod) {
			$scope.removerInstrumento = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("instrumento/excluir/"+$scope.removerInstrumento).success(function(response) {
			sucesso("Instrumento Excluído!");
			$scope.listarInstrumentos();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerInstrumento = {};
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
		$location.path('listinstrumento/');
	};
	
	$scope.sair = function () {
		$http.get("instrumento/sair").success(function(response) {
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