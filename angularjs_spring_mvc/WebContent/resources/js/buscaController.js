app.controller("buscaController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};

	
	
	$scope.listarEquipamentos = function () {
		$http.get("equipamento/listar").success(function(response) {
			console.log('equipamento listar - response-'+response);
			$scope.equipamentos = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarEquipamentos -'+response);
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
