app.controller("equipamentoController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar
		
		//alert("assasasas"+$routeParams.id);
		
		
		$http.get("equipamento/recuperar/" + $routeParams.id).success(function(response) {
			$scope.equipamento = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("fabricante/listar").success(function(response) {
					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.fabricanteesList = response;
					setTimeout(function () {
						$("#selectFabricante").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.equipamento.fabricante.id));
					}, 1000);
					
				}).error(function(data, status, headers, config) {
					erro("Error: " + status);
				});
			
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo equipamento
		
			$scope.equipamento = {};
	
	}	
	
	
	
	$scope.salvar = function () {
	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("equipamento/salvar", $scope.equipamento).success(function(response) {
			$scope.equipamento = {};
			sucesso(response);
			$location.path('listequipamento/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};
	
	
	$scope.listarEquipamentos = function () {
		$http.get("equipamento/listar").success(function(response) {
			console.log('equipamento listar - response-'+response);
			$scope.equipamentos = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarEquipamentos -'+response);
		});
	};
	
	
	$scope.editarequipamento = function(id) {
		$location.path('equipamentoEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('equipamento/cadastro/');
	};
	
	$scope.excluirEquipamento = function (cod) {
			$scope.removerEquipamento = cod;		
	};

	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);
		
		$http.post("equipamento/excluir/"+$scope.removerEquipamento).success(function(response) {
			sucesso("Equipamento Excluído!");
			$scope.listarEquipamentos();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerEquipamento = {};
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
		$location.path('listequipamento/');
	};
	

	$scope.sair = function () {
		$http.get("equipamento/sair").success(function(response) {
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
