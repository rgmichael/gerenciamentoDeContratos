app.controller("atendimentoController", function($scope, $http, $location, $routeParams, $window, $timeout) {
	$scope.selected={};
	
	$scope.temBarba = false;
	
    $scope.listaAtendimentos = [
                            {
                                id: 1,
                                data: "22/07/18",
                                servicos: ["Barba", "Cabelo"],
                                valor: 45.00,
                                atendido: true

                                
                            }, {
                                id: 2,
                                data: "23/07/18",
                                servicos: ["Cabelo"],
                                valor: 25.00,
                                atendido: false
                            }];
	

$scope.setaBarba = function () {
	alert();
	console.log($scope.selected);
	
	if ($scope.temBarba == true){
		$scope.temBarba = false;
	}else
		{
		$scope.temBarba = true;
		}
	
};


// carrega as datas de acordo com o barbeiro passado por parametro
$scope.carregarServicos = function() {
	$scope.servicos = [{}];
		$http.get("servico/listar").success(function(response) {
			console.log(response);
			$scope.servicos = response;
			console.log('lista 2 -'+$scope.servicos);
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});

};


});



//mostra msg de sucesso
function sucesso(msg) {
 	$.notify({
     	message: msg

     },{
         type: 'success',
         timer: 1000
     });
}

//mostra msg de erro
function erro(msg) {
	$.notify({
 	message: msg

 },{
     type: 'danger',
     timer: 1000
 });
}