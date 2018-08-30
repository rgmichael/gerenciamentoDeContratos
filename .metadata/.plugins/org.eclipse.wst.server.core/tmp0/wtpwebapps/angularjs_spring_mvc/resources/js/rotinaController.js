app.controller("rotinaController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("rotina/recuperar/" + $routeParams.id).success(function(response) {
			$scope.rotina = response;
			//alert("DEBUG = "+JSON.stringify($scope.rotina));
			
			//carrega e faz o seta a combo tipo rotina
				$http.get("tiporotina/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.tiporotinaList = response;
					setTimeout(function () {
						$("#selectTipoRotina").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.rotina.tipoRotina.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
					console.log('Erro - editar -'+response);
				});
				
				//carrega e faz o seta a combo equipamento
				$http.get("equipamento/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.equipamentoList = response;
					setTimeout(function () {
						$("#selectEquipamento").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.rotina.equipamento.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
					console.log('Erro - editar -'+response);
				});
				
				//carrega e faz o seta a combo instrumento
				$http.get("instrumento/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.instrumentoList = response;
					setTimeout(function () {
						$("#selectInstrumento").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.rotina.instrumento.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
					console.log('Erro - editar -'+response);
				});
				
				//carrega e faz o seta a combo area
				$http.get("area/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.areaList = response;
					setTimeout(function () {
						$("#selectArea").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.rotina.area.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
					console.log('Erro - editar -'+response);
				});						
				
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo rotina	
			$scope.rotina = {};
	}
	
	$scope.salvar = function () {	
		 //alert("SALVAR ROTINA="+$scope.rotina.servicos);
		 //alert(JSON.stringify($scope.agenda));
		$http.post("rotina/salvar", $scope.rotina).success(function(response) {
			$scope.rotina = {};
			sucesso(response);
			$location.path('listrotina/');
		}).error(function(response) {
			$scope.erros = response;
			console.log('Erro SALVAR -'+response);
	//	erro(response);
		});
	};
	
	$scope.listarRotina = function () {
		$http.get("rotina/listar").success(function(response) {
			console.log('rotina listar - response-'+response);
			$scope.rotinas = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarRotina -'+response);
		});
	};	
	
	$scope.editarrotina = function(id) {
		$location.path('rotinaEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('rotina/cadastro/');
	};
	
	$scope.excluirRotina = function (cod) {
			$scope.removerRotina = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("rotina/excluir/"+$scope.removerRotina).success(function(response) {
			sucesso("Rotina Excluída!");
			$scope.listarRotina();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerRotina = {};
	};
	

	$scope.carregarCombos = function() {
		
		if ($routeParams.id == null || $routeParams.id == undefined || $routeParams.id == ''){
			
			$scope.tiporotinaList = [{}];
			
			//chamar o metodo listar da cambo que voce pretende recuperar
			$http.get("tiporotina/listar").success(function(response) {
				console.log('tiporotinaList'+response);
				$scope.tiporotinaList = response;
			}).error(function(data, status, headers, config) {
				erro("Ocorreu um erro ao carregar a tela");
				console.log('Erro - carregarCombos -'+response);
			});
			
			//chamar o metodo listar da cambo que voce pretende recuperar
			$http.get("equipamento/listar").success(function(response) {
				console.log('equipamentoList'+response);
				$scope.equipamentoList = response;
			}).error(function(data, status, headers, config) {
				erro("Ocorreu um erro ao carregar a tela");
				console.log('Erro - carregarCombos -'+response);
			});
			
			//chamar o metodo listar da cambo que voce pretende recuperar
			$http.get("instrumento/listar").success(function(response) {
				console.log('instrumentoList'+response);
				$scope.instrumentoList = response;
			}).error(function(data, status, headers, config) {
				erro("Ocorreu um erro ao carregar a tela");
				console.log('Erro - carregarCombos -'+response);
			});

			//chamar o metodo listar da cambo que voce pretende recuperar
			$http.get("area/listar").success(function(response) {
				console.log('areaList'+response);
				$scope.areaList = response;
			}).error(function(data, status, headers, config) {
				erro("Ocorreu um erro ao carregar a tela");
				console.log('Erro - carregarCombos -'+response);
			});				
			
		}
	}

	$scope.voltar = function() {
		$location.path('listrotina/');
	};

	$scope.sair = function () {
		$http.get("rotina/sair").success(function(response) {
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
