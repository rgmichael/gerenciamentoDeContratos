app.controller("tiporotinaController", function($scope, $http, $location, $routeParams, $window) {
		
	$scope.erros = {};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o equipamento
		// entra pra editar		
		//alert("assasasas"+$routeParams.id);
		
		$http.get("tiporotina/recuperar/" + $routeParams.id).success(function(response) {
			$scope.tiporotina = response;
			//alert("eeeeeeeee"+JSON.stringify($scope.fabricante));
			//document.getElementById("imagemequipamento").src = $scope.equipamento.foto;
			
		}).error(function(data, status, headers, config) {
			erro("Ocorreu um erro ao editar");
			console.log('Erro - editar -'+response);
		});
		
	}else { // novo equipamento	
			$scope.tiporotina = {};
	}
	
	$scope.salvar = function () {	
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		// alert(JSON.stringify($scope.agenda));
		$http.post("tiporotina/salvar", $scope.tiporotina).success(function(response) {
			$scope.tiporotina = {};
			sucesso(response);
			$location.path('listtiporotina/');
		}).error(function(response) {
			$scope.erros = response;
	//	erro(response);
		});
	};
	
	$scope.listarTiporotina = function () {
		$http.get("tiporotina/listar").success(function(response) {
			console.log('tiporotina listar - response-'+response);
			$scope.tiporotinas = response;
		}).error(function(response) {
			erro("Ocorreu um erro ao listar");
			console.log('Erro - listarTiporotina -'+response);
		});
	};	
	
	$scope.editartiporotina = function(id) {
		$location.path('tiporotinaEdit/' + id);
	};
	
	$scope.novo = function() {
		$location.path('tiporotina/cadastro/');
	};
	
	$scope.excluirTiporotina = function (cod) {
			$scope.removerTiporotina = cod;		
	};
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerEquipamento);		
		$http.post("tiporotina/excluir/"+$scope.removerTiporotina).success(function(response) {
			sucesso("TipoRotina Excluída!");
			$scope.listarTiporotina();
		}).error(function(response) {
			erro("Ocorreu um erro ao excluir");
			console.log('Erro - confirmarRemocao -'+response);
		});
		$scope.removerTiporotina = {};
	};
	

	$scope.voltar = function() {
		$location.path('listtiporotina/');
	};

	$scope.sair = function () {
		$http.get("tiporotina/sair").success(function(response) {
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
