$(document).ready(function() {
	
				$('#div-agenda-eventos').fullCalendar({
					header: {
						left: 'prev,next today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					defaultDate: Date(),
				//	allDay: false,
					navLinks: true, // can click day/week names to navigate views
					editable: true,
					eventLimit: true, // allow "more" link when too many events
					eventClick: function(event) {
						
						 $(this).css('border-color', 'red');
						 
						 
							//modal exibir
						 
							$('#editar #id').text(event.id);
							$('#editar #title').text(event.title);
							$('#editar #descricao').text(event.descricao);
							$('#editar #start').text(event.start.format('DD/MM/YYYY HH:mm'));
							$('#editar #end').text(event.end.format('DD/MM/YYYY HH:mm'));
							
							var tipo = "";
							
							if (event.color == "#FFD700"){
								tipo = "Reunião";
							}
							if (event.color == "#0071c5"){
								tipo = "Serviço";
							}
							if (event.color == "#FF4500"){
								tipo = "Outros";
							}
							
							$('#editar #tipo').text(tipo);

							//modal alterar
							
							$('#editar #startHorario').val(moment(event.start).format('HH:mm'));
							$('#editar #endHorario').val(moment(event.end).format('HH:mm'));
							
							var diaInicio = moment(event.start).format('DD/MM/YYYY');
							var diaFim = moment(event.end).format('DD/MM/YYYY');

							
							$('#editar #start').val(diaInicio);
							
							   $( "#editar #start" ).prop( "disabled", true );
							
						 /*   $('#cadastrar #start').datepicker({
						    	autoclose: true,
						        format: 'dd/mm/yyyy',
						        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
						        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
						        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
						        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
						        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
						    }).datepicker("setDate", diaInicio);
							*/
							
						    $('#editar #end').datepicker({
						    	autoclose: true,
						        format: 'dd/mm/yyyy'
				
						    }).datepicker("setDate", diaFim);
							
							$('#editar #id').val(event.id);
							$('#editar #title').val(event.title);
							$('#editar #descricao').val(event.descricao);
						//	$('#editar #start').val(event.start.format('DD/MM/YYYY HH:mm'));
						//	$('#editar #end').val(event.end.format('DD/MM/YYYY HH:mm'));
							$('#editar #color').val(event.color);//tipo de evento
							
							//$("#form").css("display","none");
							//$("#visualizar").css("display","block");
							
							$("#alterar").hide();
							$("#excluir").hide();
							$("#ver").show();
				
							$('#editar').modal('show');

							return false;
							
							

					},
					lang:'pt-br',
					selectable: true,
					selectHelper: true,
					
					/*eventRender: function(event, element, view)  {   // lets test if the event has a property called holiday. 
				    // If so and it matches '1', change the background of the correct day
				 //   if (event.holiday == '1') {
				        var dateString = event.start.format("YYYY-MM-DD");
				        alert(dateString);
				        if (dateString == '2018-05-15'){

				        $(view.el[0]).find('.fc-day[data-date=' + dateString + ']')
				                        .css('background-color', '#3bb223');
				        }
				 //   }
				},*/
				
				/*  dayRender: function (date, cell) {
				      //  var dateString = event.start.format("YYYY-MM-DD");
					  
					   var today = new Date(date);
				        //alert('d='+today);
				   //     alert((today.getMonth() + 1) + '/' + today.getDate() + '/' +  today.getFullYear());
				       
					   
					   //ok
					    var dt = today.getFullYear() +'-'+(today.getMonth() + 1)+'-'+today.getDate();
				        
					 /*  var month = today.getMonth() + 1;
						  month = month.length > 1 ? month : '0' + month;

						  var day = date.getDate().toString();
						  day = day.length > 1 ? day : '0' + day;*/
					    
					//	  var dt = today.getFullYear() +'-'+(today.getMonth() + 1)+'-'+today.getDate();
					    
					    
					    
				   //    alert(dt);
					 
					   
					   
					   //  var dt = getFormattedDate(today);
					 
					   
					   
					   // alert(dt); 
				        
				       //var check = date.format('yyyy-MM-dd');
				      // alert(check);
				              //      var fer = '2018-5-31';
				             //       if (dt == fer) {
				                    	
				              //      	alert(dt + ' = ' + fer);
				              //          cell.css("background-color", "red");
				           //         }
				                    
				                /*    if (cell.hasClass('fc-other-month') == true)
				                    	cell.css('visibility','hidden');*/
				               //   $(".fc-other-month.fc-day-number").html('');
				 //   },
				
		            dayRender: function(date, cell) { //todo - trazer feriados do banco
		            //	 $(".fc-other-month.fc-day-number").html('');
                  	  var disabledDates = ["2018-05-31", "2018-05-01"];
                  	  if ($.inArray(date.format("YYYY-MM-DD"), disabledDates) > -1) {
                  	    console.log(date.format("YYYY-MM-DD"));
                  	    cell.css("background-color", "#e9fee2");
                  	  }
                  	},
					
					select: function(start, end, cell){
						
					//	if (cell.hasClass('fc-other-month') == false){
							
						var view = $('#div-agenda-eventos').fullCalendar( 'getView' );

						var mesCorrente = view.intervalStart.month()+1;
						
						
						var mesDiaSelecionado = moment(start).month()+1;
						
					
						if(mesCorrente == mesDiaSelecionado){
						
						//limpa os campos antes de exibir o formulario de cadastro
						$('#cadastrar #id').val('');
						$('#cadastrar #title').val('');
						$('#cadastrar #start').val('');
						$('#cadastrar #end').val('');
						$('#cadastrar #descricao').val('');
						$('#cadastrar #color').val('');
						
						
						
						$('#cadastrar #startHorario').val('09:00');
						$('#cadastrar #endHorario').val('10:00');
						
						var diaInicio = moment(start).format('DD/MM/YYYY');
						
				//		alert(moment(end).format('DD/MM/YYYY HH:mm'));
						$('#cadastrar #start').val(diaInicio);
						
						   $( "#cadastrar #start" ).prop( "disabled", true );
						
					 /*   $('#cadastrar #start').datepicker({
					    	autoclose: true,
					        format: 'dd/mm/yyyy',
					        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
					        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
					        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
					        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
					        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
					    }).datepicker("setDate", diaInicio);
						*/
						
					    $('#cadastrar #end').datepicker({
					    	autoclose: true,
					        format: 'dd/mm/yyyy'
			
					    }).datepicker("setDate", diaInicio);
					    
					//	$('#cadastrar #start').val(moment(start).format('DD/MM/YYYY HH:mm:ss'));
					//	$('#cadastrar #end').val(moment(end).format('DD/MM/YYYY HH:mm:ss'));
					    //alert(moment(start).format('YYYY-MM-DD'));
					    
					 //   alert();
					    
					  // carregaHorariosdisponiveis(moment(start).format('YYYY-MM-DD'));
					   
					    
						$('#cadastrar').modal('show');	
						
						}
					},
					events: {
						url: 'evento/listar'
					}
				});
				


$('#submitchange').click(function() {
					


if(validarCampos()){	
		var event2 = new Object();
		
		var inicio = $('#cadastrar #start').val();
		var fim = $('#cadastrar #end').val();

		event2.start =inicio.substring(6, 10)+'-'+inicio.substring(3, 5)+'-'+inicio.substring(0, 2)+' '+$('#cadastrar #startHorario').val();
		event2.end_time = fim.substring(6, 10)+'-'+fim.substring(3, 5)+'-'+fim.substring(0, 2)+' '+$('#cadastrar #endHorario').val();
		event2.title = $('#cadastrar #title').val();
	    event2.descricao = $('#cadastrar #descricao').val();
	    event2.cor = $('#cadastrar #color').val();
	    
	    //alert("json que vai ok="+JSON.stringify(event2));
	   
	    
	    $.ajax({
	        url: 'evento/salvar',
	        type: 'POST',
	        data: {evento: JSON.stringify(event2)},
	        success: function (response) {
	            //get the response from server and process it
	          //  alert(response);
	        	$('#cadastrar').modal('toggle'); // fecha modal = data-dismiss="modal"
	        	document.getElementById("msg").innerHTML = "Evento cadastrado com sucesso";
	        	
	        	$( "div.success" ).fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
	        	//limpa os campos apos cadastrar
				$('#cadastrar #id').val('');
				$('#cadastrar #title').val('');
				$('#cadastrar #start').val('');
				$('#cadastrar #end').val('');
				$('#cadastrar #descricao').val('');
				$('#cadastrar #color').val('');
				
	            $('#div-agenda-eventos').fullCalendar( 'refetchEvents' );
	        },
	        error: function (erro) {
	        	
	             $("#erroCadastro").html(unescape($.parseJSON(erro.responseText)));
	             $("#exibirErroCadastro").fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
	             
	        	
	        	/*var html = '';
	        	
	        	var arr = $.parseJSON(erro.responseText);
	        	
	        	$.each(arr, function (index, value) {
	        	 
	        		html += value+'<br>';
	        	});   	

	             $("#erro").html(html);
	             $("#exibirErro").fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
	        	*/

	        }
	    });
	}
	
});


});

			
function enviarAlteracao() {
	
	  if(validarCamposEditar()){
		  
	var formulario  = document.getElementById("myform");
	
	var event2 = new Object();
	
	var inicio = formulario.start.value;
	var fim = formulario.end.value;
	event2.id = formulario.id.value;
	event2.start =inicio.substring(6, 10)+'-'+inicio.substring(3, 5)+'-'+inicio.substring(0, 2)+' '+formulario.startHorario.value;
	event2.end_time = fim.substring(6, 10)+'-'+fim.substring(3, 5)+'-'+fim.substring(0, 2)+' '+formulario.endHorario.value ;
	event2.title = formulario.title.value;
    event2.descricao = formulario.descricao.value;
   event2.cor = formulario.color.value;
    
 //   alert("json que vai editar ok="+JSON.stringify(event2));
 
    
    $.ajax({
        url: 'evento/salvar',
        type: 'POST',
        data: {evento: JSON.stringify(event2)},
        success: function (response) {
            //get the response from server and process it
          //  alert(response);
        	
        	
        	
			//$("#alterar").hide();
			//$("#ver").hide();
        	
        	$('#editar').modal('toggle'); // fecha modal = data-dismiss="modal"

			
        	document.getElementById("msg").innerHTML = "Evento alterado com sucesso";
        	
        	$( "div.success" ).fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
			
            $('#div-agenda-eventos').fullCalendar( 'refetchEvents' );
        },
        error: function (erro) {
        	

             $("#erro").html(unescape($.parseJSON(erro.responseText)));
             $("#exibirErro").fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
        }
    });
	
   }
	
	
	}

//Mascara para o campo data e hora
function confirmarRemocao(){
	
//alert("exluindo id="+ $('#editar #id').val());


var event3 = new Object();

var inicio = $('#editar #start').val();
var fim = $('#editar #end').val();

event3.id = $('#editar #id').val();
event3.start =inicio.substring(6, 10)+'-'+inicio.substring(3, 5)+'-'+inicio.substring(0, 2)+' '+inicio.substring(11);
event3.end_time = fim.substring(6, 10)+'-'+fim.substring(3, 5)+'-'+fim.substring(0, 2)+' '+fim.substring(11);
event3.title = $('#editar #title').val();
event3.descricao = $('#editar #descricao').val();
event3.cor = $('#editar #color').val();


$.ajax({
    url: 'evento/excluir',
    type: 'POST',
    data: {evento: JSON.stringify(event3)},
    success: function (response) {
        //get the response from server and process it
      //  alert(response);
    	
    	document.getElementById("msg").innerHTML = "Evento excluído com sucesso";
    	
    	$( "div.success" ).fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
		
        $('#div-agenda-eventos').fullCalendar( 'refetchEvents' );
    },
    error: function (xhr) {
    	$( "div.failure" ).fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
        alert('erro'+xhr);
    }
});

}




//Mascara para o campo data e hora
function DataHora(evento, objeto){
	

	var keypress=(window.event)?event.keyCode:evento.which;
	campo = eval (objeto);
	if (campo.value == '00/00/0000 00:00:00'){
		campo.value="";
	}
 
	caracteres = '0123456789';
	separacao1 = '/';
	separacao2 = ' ';
	separacao3 = ':';
	conjunto1 = 2;
	conjunto2 = 5;
	conjunto3 = 10;
	conjunto4 = 13;
	conjunto5 = 16;
	if ((caracteres.search(String.fromCharCode (keypress))!=-1) && campo.value.length < (19)){
		if (campo.value.length == conjunto1 )
		campo.value = campo.value + separacao1;
		else if (campo.value.length == conjunto2)
		campo.value = campo.value + separacao1;
		else if (campo.value.length == conjunto3)
		campo.value = campo.value + separacao2;
		else if (campo.value.length == conjunto4)
		campo.value = campo.value + separacao3;
		else if (campo.value.length == conjunto5)
		campo.value = campo.value + separacao3;
	}else{
		event.returnValue = false;
	}
}

function getFormattedDate(date) {
	  var year = date.getFullYear();

	  var month = (1 + date.getMonth()).toString();
	  month = month.length > 1 ? month : '0' + month;

	  var day = date.getDate().toString();
	  day = day.length > 1 ? day : '0' + day;
	  
	  return  year + '-' + month  + '-' + day;
	}

function validarCampos(){
	
	var erro = "";


    if ($('#cadastrar #start').val() != "" && $('#cadastrar #end').val() != "" ) {

    	var dtInicial = new Date($('#cadastrar #start').val() +' '+ $('#cadastrar #startHorario').val());
	 	var dtFinal = new Date($('#cadastrar #end').val() +' '+ $('#cadastrar #endHorario').val());
	
    	var dataInicial = new Date(dtInicial);
    	var dataFinal = new Date(dtFinal);


	    if (dataInicial >= dataFinal) {

	    	erro += "Horário inicial maior ou igual que o final <br>";
	    } 
	}

	if ($('#cadastrar #start').val() == "") {

	    	erro += "Selecione a Data Inicial <br>";
	}

	if ($('#cadastrar #end').val() == "") {

	    	erro += "Selecione a Data Final <br>";
	}

	if ($('#cadastrar #title').val() == "") {

	    	erro += "Informe o Título <br>";
	}

	if ($('#cadastrar #descricao').val() == "") {

	    	erro += "Informe a Descrição <br>";
	}

	if ($('#cadastrar #color').val() == "") {

	    	erro += "Selecione o Tipo <br>";
	}
	
	if ($('#cadastrar #startHorario').val() == "") {

    	erro += "Selecione o Horário de Início <br>";
}
	
	if ($('#cadastrar #startHorario').val() == "0") {

    	erro += "Não existem horários liberados para esse dia! <br>";
}
	
	if ($('#cadastrar #endHorario').val() == "") {

    	erro += "Selecione o Horário do Fim <br>";
}


    if (erro != ""){
    	document.getElementById("erroCadastro").innerHTML = erro;
    	$( "#exibirErroCadastro" ).fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
        return false;

    }else{
    	return true;
    }
}


function validarCamposEditar(){
	
	var erro = "";
	
	var formulario  = document.getElementById("myform");
	
	var event2 = new Object();
	
	var inicio = formulario.start.value;
	var fim = formulario.end.value;
	event2.id = formulario.id.value;
	event2.start =inicio.substring(6, 10)+'-'+inicio.substring(3, 5)+'-'+inicio.substring(0, 2)+' '+formulario.startHorario.value;
	event2.end_time = fim.substring(6, 10)+'-'+fim.substring(3, 5)+'-'+fim.substring(0, 2)+' '+formulario.endHorario.value ;
	event2.title = formulario.title.value;
    event2.descricao = formulario.descricao.value;
   event2.cor = formulario.color.value;

    if (event2.start != "" && event2.end_time != "" ) {

	
    	var dataInicial = new Date(event2.start);
    	var dataFinal = new Date(event2.end_time);

	    if (dataInicial >= dataFinal) {

	    	erro += "Horário inicial maior ou igual que o final <br>";
	    } 
	}

	if (inicio == "") {

	    	erro += "Selecione a Data Inicial <br>";
	}

	if (fim == "") {

	    	erro += "Selecione a Data Final <br>";
	}

	if (event2.title == "") {

	    	erro += "Informe o Título <br>";
	}

	if (event2.descricao == "") {

	    	erro += "Informe a Descrição <br>";
	}

	if (event2.cor == "") {

	    	erro += "Selecione o Tipo <br>";
	}
	
	if (formulario.startHorario.value == "") {

    	erro += "Selecione o Horário de Início <br>";
}
	
	
	if (formulario.endHorario.value == "") {

    	erro += "Selecione o Horário do Fim <br>";
}


    if (erro != ""){
    	document.getElementById("erro").innerHTML = erro;
    	$( "#exibirErro" ).fadeIn( 600 ).delay( 5000 ).fadeOut( 800 );
        return false;

    }else{
    	return true;
    }
}
/*
function carregaHorariosdisponiveis(inicio){

alert("inicio"+inicio);
			$.getJSON('evento/listarHorarios/'+inicio, function (dados){
			
			if (dados.length > 0){ 	

				var option = '<option value="">Selecione um Horário</option>';

				$.each(dados, function(i, obj){
		
					 option += '<option value="'+obj+'">'+obj+'</option>';

				})
				
			}else{

				$('#startHorario').empty().append('<option value="0">--</option>');
				$('#endHorario').empty().append('<option value="0">--</option>>');
			}

			$('#startHorario').html(option).show();
			$('#endHorario').html(option).show();
		})

}
	
	
function recarregaHorariosdisponiveis(){
	
var fim = $('#cadastrar #end').val();
	
var ini = $('#cadastrar #start').val();

	
	if (ini != fim && fim != ""){		
	
		fim = fim.substring(6, 10)+'-'+fim.substring(3, 5)+'-'+fim.substring(0, 2);

					$.getJSON('evento/listarHorarios/'+fim, function (dados2){
						
						if (dados2.length > 0){ 	

							var option2 = '<option value="">Selecione um Horário</option>';

							$.each(dados2, function(i, obj){
					
								 option2 += '<option value="'+obj+'">'+obj+'</option>';

							})
							
						}else{

							$('#endHorario').empty().append('<option value="0">--</option>>');
						}

						$('#endHorario').html(option2).show();
					})
					
	}

}
*/