app.controller("fabricanteController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != '')
	{
		// se estiver editando o fabricante
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("fabricante/recuperar/" + $routeParams.id).success(function(response) {
			$scope.fabricante = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.fabricante));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo fabricante		
			$scope.fabricante = {};	
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.fabricante));
		$http.post("fabricante/salvar", $scope.fabricante).success(function(response) {
			$scope.fabricante = {};
			sucesso(response);
			$location.path('listfabricante/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};	
	
	$scope.listarFabricantes = function () {
		$http.get("fabricante/listar").success(function(response) {
			console.log('fabricante listar - response-'+response);
			$scope.fabricantes = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarFabricante -'+response);
		});
	};	
	
	$scope.editarfabricante = function(id) {
		$location.path('fabricanteEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('fabricante/cadastro/');
	};
	
	$scope.excluirFabricante = function (cod) {
			$scope.removerFabricante = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("fabricante/excluir/"+$scope.removerFabricante).success(function(response) {
			sucesso("Fabricante Excluído!");
			$scope.listarFabricantes();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerFabricante = {};
	};	
	
	$scope.voltar = function() {
		$location.path('listfabricante/');
	};
	
	$scope.sair = function () {
		$http.get("fabricante/sair").success(function(response) {
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