
// configura��o do m�dulo
var app = angular.module('agenda', [ 'ngRoute', 'ngResource', 'ngAnimate' ]);


// configurando rotas
app.config(function($routeProvider) {

			$routeProvider.when("/publico/cadastro", {
				controller : "usuarioController",
				templateUrl : "publico/cadastro.html"
			})// novo
			
			.otherwise({
				redirectTo : "/"
			});
			
			
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



