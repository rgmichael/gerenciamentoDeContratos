app.controller("usuarioController", function($scope, $http, $location, $routeParams, $window, $timeout) {
	
	$scope.usuario = {};


	$scope.disableButton = false;
		
	$scope.msgNome = false;
	$scope.msgEmail = false;
	$scope.msgCpf = false;
	$scope.msgPerfil = false;
	$scope.msgSenha = false;
	$scope.msgCpfInvalido = false;
	$scope.msgEmailInvalido = false;
	$scope.msgConfereSenha = false;
	$scope.msgUsuarioJaExiste = false;
	
	$scope.titulo = "";
	
	$scope.email = "";
	$scope.msgErroRedefinir = "";
	$scope.msgOk = "";
	$scope.errosRedefirnirSenha = false;
	$scope.okRedefirnirSenha = false;
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar
		
		//alert("assasasas"+$routeParams.id);
		
		$scope.titulo = "Editar";
		
		
		$http.get("usuario/recuperar/" + $routeParams.id).success(function(response) {
			$scope.usuario = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.equipamento));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
			//carrega e seta combo fabricante
				$http.get("perfil/listar").success(function(response) {
					
				//	alert("fabricanteesList edit-"+JSON.stringify(response));
					$scope.perfilList = response;
					setTimeout(function () {
						$("#selectPerfil").prop('selectedIndex', buscarKeyJson(response, 'id', $scope.usuario.perfil.id));
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
			
			$scope.titulo = "Incluir";
	}	
	
// ----------------------
	
	$scope.listarUsuarios = function () {
		$http.get("usuario/listar").success(function(response) {
			console.log('usuario listar - response-'+response);
			$scope.usuarios = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarUsuarios -'+response);
		});
	};
	
	
	$scope.editarUsuario = function(id) {
		$location.path('usuarioEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('usuario/cadastro/');
	};
	
	$scope.excluirUsuario = function (cod) {
			$scope.removerUsuario = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
		//	alert("removendo= " + $scope.removerUsuario);
			
			$http.post("usuario/excluir/"+$scope.removerUsuario).success(function(response) {
				sucesso("Usuário Excluído!");
				$scope.listarUsuarios();
			}).error(function(response) {
				erro("Ocorreu um erro ao excluir");
				console.log('Erro - confirmarRemocao -'+response);
			});
			$scope.removerUsuario = {};
		};
		
	$scope.listagem = function() {
		$location.path('listUsuario/');
	};
	
	
	
	$scope.ativarInativar = function (cod) {
		//alert(cod);	
		
		$http.post("usuario/ativarInativar/"+cod).success(function(response) {
			sucesso("Status Alterado!");
			$scope.listarUsuarios();
		}).error(function(response) {
			erro("Ocorreu um erro ao Ativar ou Inativar");
			console.log('Erro - ativarInativar -'+response);
		});
};
	
	$scope.cadastrar = function () {

		// alert(JSON.stringify($scope.agenda));
		
		$scope.disableButton = true;
		$scope.msgNome = false;
		$scope.msgEmail = false;
		$scope.msgCpf = false;
		$scope.msgPerfil = false;
		$scope.msgSenha = false;
		$scope.msgCpfInvalido = false;
		$scope.msgEmailInvalido = false;
		$scope.msgConfereSenha = false;
		$scope.msgUsuarioJaExiste = false;
		
		//alert(JSON.stringify($scope.usuario));
		
		$http.post("usuario/salvar", $scope.usuario).success(function(response) {
			$scope.usuario = {};
			sucesso(response);
			$location.path('listUsuario/');
		}).error(function(response) {
			
			$scope.erros = response;
			
			if($scope.erros.indexOf('1') != -1){
				$scope.msgNome = true;
			}
			if($scope.erros.indexOf('2') != -1){
				$scope.msgEmail = true;
			}
			if($scope.erros.indexOf('3') != -1){
				$scope.msgCpf = true;
			}
			if($scope.erros.indexOf('4') != -1){
				$scope.msgPerfil = true;
			}
			if($scope.erros.indexOf('5') != -1){
				$scope.msgSenha = true;
			}
			if($scope.erros.indexOf('6') != -1){
				$scope.msgCpfInvalido = true;
			}
			if($scope.erros.indexOf('7') != -1){
				$scope.msgEmailInvalido = true;
			}
			if($scope.erros.indexOf('8') != -1){
				$scope.msgConfereSenha = true;
			}
			if($scope.erros.indexOf('9') != -1){
				$scope.msgUsuarioJaExiste = true;
			}

			
			
			$scope.disableButton = false;
			
		}).finally(function () {
		      $scope.disableButton = false;
		    });
	};
	
	$scope.carregarCombos = function() {
		
		if ($routeParams.id == null || $routeParams.id == undefined || $routeParams.id == ''){
			
			$scope.perfilList = [{}];
			
			$http.get("perfil/listar").success(function(response) {
				console.log('perfilList='+response);
				$scope.perfilList = response;
			}).error(function(data, status, headers, config) {
				erro("Ocorreu um erro ao carregar a combo");
				console.log('Erro - carregarCombos -'+response);
			});
		}
	}
	
	
// ----------------------	
	
	$scope.voltar = function() {
		$window.location.href = '../publico/login.html';
	};
	
	
	
	$scope.redefirnirSenha = function () {
		
		$scope.errosRedefirnirSenha = false;
		
		$scope.okRedefirnirSenha = false;
		
		$scope.disableButtonRedefirnirSenha = true;

		
		$http.post("../usuario/redefirnirSenha", $scope.email).success(function(response) {
			//alert(response);

			$scope.msgOk = response;
			
			$scope.okRedefirnirSenha = true;

	        $timeout(function () {
	        	$window.location.href = '../publico/login.html';
	        }, 4000);
			
		}).error(function(response) {
			$scope.msgErroRedefinir = response;
			
			$scope.errosRedefirnirSenha = true;
			
			$scope.disableButtonRedefirnirSenha = false;
			
		}).finally(function () {
			  $scope.disableButtonRedefirnirSenha = false;
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