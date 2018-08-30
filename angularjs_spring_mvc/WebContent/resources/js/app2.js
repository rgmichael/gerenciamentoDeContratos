var usuario = "";
var permissoes = "";
var permissao = false;
// configuraçãoo do módulo
var app = angular.module('agenda', [ 'ngRoute', 'ngResource', 'ngAnimate', 'angularUtils.directives.dirPagination' ]);


// configurando rotas
app.config(function($routeProvider) {

			$routeProvider.when("/agendalistBarbeiro", {
				controller : "agendaControllerBarbeiro",
				templateUrl : "agendaBarbeiro/list.html"
			})// listar
			
			
			$routeProvider.when("/agendaBarbeiro/cadastro", {
				controller : "agendaControllerBarbeiro",
				templateUrl : "agendaBarbeiro/cadastro.html"
			})// cadastro
			
			
			
			$routeProvider.when("/agendalistCliente", {
				controller : "agendaController",
				templateUrl : "agendaCliente/list.html"
			})// listar
			
			
			$routeProvider.when("/agendaCliente/cadastro", {
				controller : "agendaController",
				templateUrl : "agendaCliente/cadastro.html"
			})// listar
			
			
			$routeProvider.when("/agendaCliente/senha", {
				controller : "agendaController",
				templateUrl : "agendaCliente/senha.html"
			})// listar
			
			$routeProvider.when("/agenda/cadastro", {
				controller : "agendaController",
				templateUrl : "agenda/cadastro.html"
			})// cadastro
						
			
			$routeProvider.when("/agendaedit/:id", {
				controller : "agendaController",
				templateUrl : "agendaCliente/cadastro.html"
			})// cadastro
					
			
			$routeProvider.when("/publico/cadastro", {
				controller : "usuarioController",
				templateUrl : "publico/cadastro.html"
			})// novo
			
			
			//equipamento**************
	
			$routeProvider.when("/listequipamento", {
				controller : "equipamentoController",
				templateUrl : "equipamento/list.html"
			})// listar
			
			
			$routeProvider.when("/equipamento/cadastro", {
				controller : "equipamentoController",
				templateUrl : "equipamento/cadastro.html"
			})// cadastro
			
			$routeProvider.when("/equipamento/senha", {
				controller : "equipamentoController",
				templateUrl : "equipamento/senha.html"
			})// listar
			
			$routeProvider.when("/equipamentoEdit/:id", {
				controller : "equipamentoController",
				templateUrl : "equipamento/cadastro.html"
			})// editar
			
			
			
			//usuario
			
			$routeProvider.when("/listUsuario", {
				controller : "usuarioController",
				templateUrl : "usuario/list.html"
			})// listar
			
			$routeProvider.when("/usuario/cadastro", {
				controller : "usuarioController",
				templateUrl : "usuario/cadastro.html"
			})// cadastro
			
			$routeProvider.when("/usuarioEdit/:id", {
				controller : "usuarioController",
				templateUrl : "usuario/cadastro.html"
			})// editar
			
			
			
			//evento
			
			$routeProvider.when("/listEvento", {
				controller : "eventoController",
				templateUrl : "evento/calendario.html"
			})// listar
			
			
		//AREA **************
	
			$routeProvider.when("/listarea", {
				controller : "areaController",
				templateUrl : "area/list.html"
			})// listar
			
			
			$routeProvider.when("/area/cadastro", {
				controller : "areaController",
				templateUrl : "area/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/areaEdit/:id", {
				controller : "areaController",
				templateUrl : "area/cadastro.html"
			})// cadastro			
			
			//INSUMOS **************
	
			$routeProvider.when("/listinsumo", {
				controller : "insumoController",
				templateUrl : "insumo/list.html"
			})// listar
			
			
			$routeProvider.when("/insumo/cadastro", {
				controller : "insumoController",
				templateUrl : "insumo/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/insumoEdit/:id", {
				controller : "insumoController",
				templateUrl : "insumo/cadastro.html"
			})// cadastro						
			
			//INSTRUMENTO **************
	
			$routeProvider.when("/listinstrumento", {
				controller : "instrumentoController",
				templateUrl : "instrumento/list.html"
			})// listar
			
			
			$routeProvider.when("/instrumento/cadastro", {
				controller : "instrumentoController",
				templateUrl : "instrumento/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/instrumentoEdit/:id", {
				controller : "instrumentoController",
				templateUrl : "instrumento/cadastro.html"
			})// cadastro				
			

			//Fabricante ************** fabricante
	
			$routeProvider.when("/listfabricante", {
				controller : "fabricanteController",
				templateUrl : "fabricante/list.html"
			})// listar
			
			
			$routeProvider.when("/fabricante/cadastro", {
				controller : "fabricanteController",
				templateUrl : "fabricante/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/fabricanteEdit/:id", {
				controller : "fabricanteController",
				templateUrl : "fabricante/cadastro.html"
			})// cadastro				
						
			
			//Contratante ************** contratante
	
			$routeProvider.when("/listcontratante", {
				controller : "contratanteController",
				templateUrl : "contratante/list.html"
			})// listar
			
			
			$routeProvider.when("/contratante/cadastro", {
				controller : "contratanteController",
				templateUrl : "contratante/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/contratanteEdit/:id", {
				controller : "contratanteController",
				templateUrl : "contratante/cadastro.html"
			})// cadastro
			
			
			//TipoRotina ************** tiporotina
	
			$routeProvider.when("/listtiporotina", {
				controller : "tiporotinaController",
				templateUrl : "tiporotina/list.html"
			})// listar
			
			
			$routeProvider.when("/tiporotina/cadastro", {
				controller : "tiporotinaController",
				templateUrl : "tiporotina/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/tiporotinaEdit/:id", {
				controller : "tiporotinaController",
				templateUrl : "tiporotina/cadastro.html"
			})// cadastro
			
			
			//TipoEquipamento ************** tipoequipamento
	
			$routeProvider.when("/listtipoequipamento", {
				controller : "tipoequipamentoController",
				templateUrl : "tipoequipamento/list.html"
			})// listar
			
			
			$routeProvider.when("/tipoequipamento/cadastro", {
				controller : "tipoequipamentoController",
				templateUrl : "tipoequipamento/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/tipoequipamentoEdit/:id", {
				controller : "tipoequipamentoController",
				templateUrl : "tipoequipamento/cadastro.html"
			})// cadastro						

			
			//TipoArea ************** tipoarea
	
			$routeProvider.when("/listtipoarea", {
				controller : "tipoareaController",
				templateUrl : "tipoarea/list.html"
			})// listar
			
			
			$routeProvider.when("/tipoarea/cadastro", {
				controller : "tipoareaController",
				templateUrl : "tipoarea/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/tipoareaEdit/:id", {
				controller : "tipoareaController",
				templateUrl : "tipoarea/cadastro.html"
			})// cadastro

			
			//TipoArea ************** tipoarea
	
			$routeProvider.when("/listrotina", {
				controller : "rotinaController",
				templateUrl : "rotina/list.html"
			})// listar
			
			
			$routeProvider.when("/rotina/cadastro", {
				controller : "rotinaController",
				templateUrl : "rotina/cadastro.html"
			})// cadastro
			
			
			$routeProvider.when("/rotinaEdit/:id", {
				controller : "rotinaController",
				templateUrl : "rotina/cadastro.html"
			})// cadastro
			
			$routeProvider.when("/buscaMultipla", {
				controller : "buscaController",
				templateUrl : "buscaMultipla/list.html"
			})// busca
			
			// redirecionando para raiz

			.otherwise({
				redirectTo : "/"
			});
			
			
});


//faz a identificação da posição correta da cidade do registro para mostrar em edição
function buscarKeyJson(obj, key, value)
{
    for (var i = 0; i < obj.length; i++) {
        if (obj[i][key] == value) {
            return i + 2;
        }
    }
    return null;
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


//mostra msg de sucesso
function sucesso(msg) {
 	$.notify({
     	message: msg

     },{
         type: 'success',
         timer: 1000
     });
}

/*
//carregar datas disponiveis do barbeiro quando � navegador chrome usando jQuery
function carregarHorariosChrome(barbeiro) {
	if (identific_nav() === 'chrome') {// executa se for chrome
		$.get("barbeiros/listarchrome", { idBarbeiro : barbeiro.value}, function(data) {
			 var json = JSON.parse(data);
			 html = '<option value="">--Selecione--</option>';
			 for (var i = 0; i < json.length; i++) {
				  html += '<option value='+json[i].id+'>'+json[i].data_hora_agendamento+'</option>';
			 }
			 $('#selectHorarios').html(html);
		});
  }
}

*/
//identificar navegador
function identific_nav(){
    var nav = navigator.userAgent.toLowerCase();
    if(nav.indexOf("msie") != -1){
       return browser = "msie";
    }else if(nav.indexOf("opera") != -1){
    	return browser = "opera";
    }else if(nav.indexOf("mozilla") != -1){
        if(nav.indexOf("firefox") != -1){
        	return  browser = "firefox";
        }else if(nav.indexOf("firefox") != -1){
        	return browser = "mozilla";
        }else if(nav.indexOf("chrome") != -1){
        	return browser = "chrome";
        }
    }else{
    	alert("Navegador desconhecido!");
    }
}

