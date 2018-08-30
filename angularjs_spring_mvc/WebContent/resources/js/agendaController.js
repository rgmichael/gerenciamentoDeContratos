app.controller("agendaController", function($scope, $http, $location, $routeParams, $window) {
	
	$scope.agenda = {};
	

	 
	 
	 
	 $scope.barbeiroFixo = "";
	 
	 $scope.novasenha = "";
	 $scope.novasenharepita = "";
	 
	 
	 
	

	  // Selected fruits
	  $scope.selection = []; // new Array();// [];
	  
	  

	  // Toggle selection for a given fruit by name
	  $scope.toggleSelection = function toggleSelection(fruitName) {
	    var idx = $scope.selection.indexOf(fruitName);

	    // Is currently selected
	    if (idx > -1) {
	      $scope.selection.splice(idx, 1);
	    }

	    // Is newly selected
	    else {
	      $scope.selection.push({ id: fruitName.toString() });
	    }
	    console.log("ssssssssss="+$scope.selection);
	  };
	 
	 
	 
	 
	 
	 
	 
	 
	 

	$scope.listarAgenda = function () {
		$http.get("agenda/listar").success(function(response) {
			console.log('listarAgenda - response-'+response);
			$scope.marcacoes = response;
		}).error(function(response) {
			alert("Erro - "+ response);
		});
	};
	
	
	
	
	
	
	
	
	$scope.removerAgenda = function (codAgenda) {
			$scope.removerAtendimento = codAgenda;		
	};
	
	
	
	
	
	
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerAtendimento);
		
		$http.post("agenda/deletar/"+$scope.removerAtendimento).success(function(response) {
			sucesso("Seu agendamento foi cancelado!")
			$scope.listarAgenda();
		}).error(function(response) {
			alert("Error: " + response);
		});
		$scope.removerAtendimento = {};
	};
	
	
	
	$scope.removerAgendaOld = function (codAgenda) {
		$http.delete("agenda/deletar/"+codAgenda).success(function(response) {
			$scope.listarAgenda();
		}).error(function(response) {
			alert("Error: " + response);
		});
	};
	
	
	
	
	$scope.salvarAgenda = function () {
		
		
		// alert("barbeiroFixo="+$scope.barbeiroFixo.id);
		 

		 
		 
		 $scope.agenda.servicos = $scope.selection;
		 $scope.agenda.barbeiro = $scope.barbeiroFixo;
		 
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		
		 
		// alert(JSON.stringify($scope.agenda));
		 
		$http.post("agenda/salvar", $scope.agenda).success(function(response) {
			$scope.agenda = {};
			sucesso("Agendado com sucesso!");
			$location.path('agendalistCliente/');
		}).error(function(response) {
			//erro("Error: " + response);
			//alert(response);
		erro(response);
			//$scope.erros = response;
		});
	};
	
	
	$scope.editarAgenda = function(id) {
		$location.path('agendaedit/' + id);
	};
	
	if ($routeParams.id != null && $routeParams.id != undefined
			&& $routeParams.id != ''){// se estiver editando o cliente
		// entra pra editar
		$http.get("agenda/buscarAgenda/" + $routeParams.id).success(function(response) {
			$scope.agenda = response;
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
		
	}else { // novo cliente
		$scope.agenda = {};
	}
	
	
	// carrega os barbeiros ao iniciar a tela de agendamento 
	$scope.carregarBarberios = function() {
		$scope.dataBarbeiros = [{}];
		$http.get("barbeiros/listar").success(function(response) {
			console.log('dataBarbeiros-'+response);
			$scope.dataBarbeiros = response;
		}).error(function(response) {
			erro("Error: " + response);
		});
	};
	
	
	// carrega os clientes ao iniciar a tela de agendamento  do barbeiro
	$scope.carregarClientes = function() {
		$scope.dataClientes = [{}];
		$http.get("clientes/listar").success(function(response) {
			console.log('dataClientes-'+response);
			$scope.dataClientes = response;
		}).error(function(response) {
			erro("Error: " + response);
		});
	};
	
	// carrega as datas de acordo com o barbeiro passado por parametro
	$scope.carregarHorariosOld = function(barbeiro) {
	
		$scope.horarios = {};
	//	if (identific_nav() != 'chrome') {// executa se for diferente do chrome
			$http.get("agenda/listarHorariosBarbeiros/" + barbeiro.id).success(function(response) {
				$scope.horarios = response;
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
	//  }
	};
	
	
	// carrega as datas de acordo com o barbeiro passado por parametro
	$scope.carregarHorarios = function() {
		

	//	alert('vv'+$scope.agenda.dia);
//		alert('bb-'+$scope.agenda.barbeiro);
		
	//	alert('sss-'+$scope.barbeiroFixo.id);
			
			//alert('id-'+$scope.agenda.barbeiro.id);
			
		//	$http.get("agenda/listarHorariosBarbeiros/" + $scope.agenda.barbeiro.id +"/" + $scope.agenda.dia).success(function(response) {
		//	$http.get("agenda/listarHorariosBarbeiros/" + $scope.agenda.barbeiro.id +"/" + $scope.agenda.dia.replace(/\//g,"-")).success(function(response) {
				$http.get("agenda/listarHorariosBarbeiros/" + $scope.barbeiroFixo.id +"/" + $scope.agenda.dia.replace(/\//g,"-")).success(function(response) {
				$scope.horarios = response;
			}).error(function(data, status, headers, config) {
				alert("Error: " + status);
			});
			

		
		/*	
		$scope.horarios = {};
		
		
		
		
		alert($scope.dia);
		alert($scope.barbeiro.id);
	
	//	if (identific_nav() != 'chrome') {// executa se for diferente do chrome
			$http.get("agenda/listarHorariosBarbeiros/" + $scope.barbeiro.id +"/" + $scope.dia).success(function(response) {
				$scope.horarios = response;
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
	//  }*/
	};
	
	// carrega as datas de acordo com o barbeiro passado por parametro
	$scope.carregarServicos = function() {
		$scope.servicos = [{}];
	//	if (identific_nav() != 'chrome') {// executa se for diferente do chrome
			$http.get("servico/listar").success(function(response) {
				console.log(response);
				$scope.servicos = response;
				console.log('lousta 2 -'+$scope.servicos);
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
	//  }
	};
	
	$scope.voltar = function() {
		$location.path('agendalistCliente/');
	};
	
	
	$scope.limpaHorarios = function() {
		$scope.horarios = {};
	};
	
	$scope.sair = function () {
		$http.get("agenda/sair").success(function(response) {
			//sucesso('Até Mais!');
			 $window.location.reload();
		}).error(function(response) {
			erro(response);
		});
	};
	
	
	
	$scope.alterarSenha = function () {
		alert('123');
			$http.post("agenda/trocar", $scope.novasenha).success(function(response) {
				sucesso("Senha Alterada com sucesso!");
				$scope.listarAgenda();
			}).error(function(response) {
				erro(response);
			});
		};
		
		
		
		
		
		$scope.alterarSenha = function () {
					 
			$http.post("agenda/trocar", $scope.novasenha).success(function(response) {
				sucesso("Senha trocada com sucesso!");
				$location.path('agendalistCliente/');
			}).error(function(response) {

			erro(response);

			});
		};
		

});


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
