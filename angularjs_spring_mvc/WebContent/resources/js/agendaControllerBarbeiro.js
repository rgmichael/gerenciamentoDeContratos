app.controller("agendaControllerBarbeiro", function($scope, $http, $location, $routeParams,$filter,$window) {
	
	
	$scope.agenda = {};
	
	
	
	 var data = new Date();
	$scope.dataFormatada = $filter('date')(data, 'dd/MM/yyyy');
	 
	 
	/*
	
	$scope.isEven = function(value) {
		
		var data1 = new Date(value);
		var data2 = new Date();

		if (data1 >= data2)
		  return true;
		
		else 
		  return false;

		};*/
	
	 
	 $scope.barbeiroFixo = "";
	 
	 
	 
	 
	 
	

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
	 
	 
	 
	 
	 
	 
	 
	 
	 

	$scope.listarAgendaBarbeiro = function () {

		$http.get("agenda/listarBarbeiro").success(function(response) {
			console.log('listarAgenda - response-'+response);
			$scope.marcacoes = response;
		}).error(function(response) {
			alert("Erro - "+ response);
		});
	};
	
	
	$scope.listarAgendaBarbeiroPorDia = function () {
		$scope.procurar = '';
		if($scope.dia == '' || $scope.dia == undefined){
			erro("Preencha o Dia");
		}
		else{
			$http.get("agenda/listarBarbeiroPorDia/"+$scope.dia.replace(/\//g,"-")).success(function(response) {
				console.log('listarAgendaBarbeiroPorDia - response-'+response);
				$scope.marcacoes = response;
				
			}).error(function(response) {
				alert("Erro - "+ response);
			});
		}
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
	
	
	$scope.salvarAgenda = function () {
		
		
		// alert("barbeiroFixo="+$scope.barbeiroFixo.id);
		 

		 
		 
		 $scope.agenda.servicos = $scope.selection;
		// $scope.agenda.barbeiro = $scope.barbeiroFixo;
		 
		 //alert("ttttttttttttt="+$scope.agenda.servicos);
		
		 
		// alert(JSON.stringify($scope.agenda));
		 
		$http.post("agenda/salvarAgendaBarbeiro", $scope.agenda).success(function(response) {
			$scope.agenda = {};
			sucesso("Agendado com sucesso!");
			$location.path('agendalistBarbeiro/');
		}).error(function(response) {
			//erro("Error: " + response);
			//alert(response);
		erro(response);
			//$scope.erros = response;
		});
	};
	
	
		
	$scope.removerAgenda = function (codAgenda) {
			$scope.removerAtendimento = codAgenda;		
	};
	
	
	
	
	
	
	
	$scope.confirmarRemocao = function () {
		
	//	alert("removendo= " + $scope.removerAtendimento);
		
		$http.post("agenda/deletar/"+$scope.removerAtendimento).success(function(response) {
			sucesso("Seu agendamento foi cancelado!")
			$scope.listarAgendaBarbeiro();
		}).error(function(response) {
			alert("Error: " + response);
		});
		$scope.removerAtendimento = {};
	};
	
	
	$scope.realizarAtendimento = function (codAgenda) {	
			$http.post("agenda/atender/"+codAgenda).success(function(response) {
				sucesso("Pronto!")
				$scope.listarAgendaBarbeiro();
			}).error(function(response) {
				alert(response);
			});
		};
	
	
	
	// carrega as datas de acordo com o barbeiro passado por parametro
	$scope.carregarHorarios = function() {
		

	//	alert('vv'+$scope.agenda.dia);
//		alert('bb-'+$scope.agenda.barbeiro);
		
	//	alert('sss-'+$scope.barbeiroFixo.id);
			
			//alert('id-'+$scope.agenda.barbeiro.id);
			
		//	$http.get("agenda/listarHorariosBarbeiros/" + $scope.agenda.barbeiro.id +"/" + $scope.agenda.dia).success(function(response) {
		//	$http.get("agenda/listarHorariosBarbeiros/" + $scope.agenda.barbeiro.id +"/" + $scope.agenda.dia.replace(/\//g,"-")).success(function(response) {
				$http.get("agenda/listarHorarios/" + $scope.agenda.dia.replace(/\//g,"-")).success(function(response) {
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
				$scope.servicos = response;
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
	//  }
	};
	
	$scope.voltar = function() {
		$location.path('agendalistBarbeiro/');
	};
	
	
	$scope.limpar = function() {
		$scope.procurar = '';
	};
	
	$scope.sair = function () {
				$http.get("agenda/sair").success(function(response) {
					//sucesso('Até Mais!');
					 $window.location.reload();
				}).error(function(response) {
					erro(response);
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