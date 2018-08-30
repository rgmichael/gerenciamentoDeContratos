app.controller("areaController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("area/recuperar/" + $routeParams.id).success(function(response) {
			$scope.area = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.area.fabricante.id));
					}, 1000);					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo equipamento	
			$scope.area = {};
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("area/salvar", $scope.area).success(function(response) {
			$scope.area = {};
			sucesso(response);
			$location.path('listarea/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};
	
	$scope.listarArea = function () {
		$http.get("area/listar").success(function(response) {
			console.log('area listar - response-'+response);
			$scope.areas = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarArea -'+response);
		});
	};	
	
	$scope.editararea = function(id) {
		$location.path('areaEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('area/cadastro/');
	};
	
	$scope.excluirArea = function (cod) {
			$scope.removerArea = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("area/excluir/"+$scope.removerArea).success(function(response) {
			sucesso("Area Excluída!");
			$scope.listarArea();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerArea = {};
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
		$location.path('listarea/');
	};

	$scope.sair = function () {
		$http.get("area/sair").success(function(response) {
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
