app.controller("eventoController", function($scope, $http, $location, $routeParams, $window, $timeout) {
	
	$scope.eventos = {};


	
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