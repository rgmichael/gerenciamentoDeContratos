package curso.angular.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import curso.angular.dao.DaoImplementacao;
import curso.angular.dao.DaoInterface;
import curso.angular.model.Agenda;
import curso.angular.model.Usuario;



@Controller
@RequestMapping(value="/agenda")
public class AgendaController extends DaoImplementacao<Agenda> implements 
		DaoInterface<Agenda> {
	
	@Autowired
	private UsuarioController usu;
	
	@Autowired
	private JavaMailSender mailSender;

	  
	   

	public AgendaController(Class<Agenda> persistenceClass) { 
		super(persistenceClass); 
	}
	
	
	
	@RequestMapping(value="listar", method=RequestMethod.GET, headers = "Accept=application/json" )
	@ResponseBody
	public byte[] listar() throws Exception {
		
		
		

         
		
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		
		
		System.out.println("usuario listar="+ email);
		
		Usuario usu2 = new Usuario();
		
		usu2 = usu.recuperarUsuario(email);
		
		
		//System.out.println("lista tamanhp="+ super.lista().size());
		
		System.out.println("usuario getId="+ usu2.getId().toString());
		
		/*Agenda agenda = new Agenda();
		agenda.setCod_agenda(10L);
		agenda.setObs("aaa");
		agenda.setfotoCliente("https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-1/p160x160/20882887_1412761472106603_88755453766747040_n.jpg?oh=469ac39bee26d06059dc74fff853309b&oe=5A658647");
		agenda.setData_hora_agendamento(new Date());
		
		List<Agenda> agendas = new ArrayList<Agenda>();
		
		agendas.add(agenda);
		
		System.out.println("aaaaa");*/
		
		
	
		
		return new Gson().toJson(super.listaAgendaCliente(usu2.getId())).getBytes("UTF-8"); 
	}
	  
	
	
	
	@RequestMapping(value="listarBarbeiro", method=RequestMethod.GET, headers = "Accept=application/json" )
	@ResponseBody
	public byte[] listarBarbeiro() throws Exception {
		
		
		

         
		
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		
		
		System.out.println("usuario listarBarbeiro="+ email);
		
		Usuario usu2 = new Usuario();
		
		usu2 = usu.recuperarUsuario(email);
		
		
		//System.out.println("lista tamanhp="+ super.lista().size());
		
		System.out.println("usuario getId="+ usu2.getId().toString());
		
		/*Agenda agenda = new Agenda();
		agenda.setCod_agenda(10L);
		agenda.setObs("aaa");
		agenda.setfotoCliente("https://scontent.fgig4-1.fna.fbcdn.net/v/t1.0-1/p160x160/20882887_1412761472106603_88755453766747040_n.jpg?oh=469ac39bee26d06059dc74fff853309b&oe=5A658647");
		agenda.setData_hora_agendamento(new Date());
		
		List<Agenda> agendas = new ArrayList<Agenda>();
		
		agendas.add(agenda);
		
		System.out.println("aaaaa");*/
		
		
	
		
		return new Gson().toJson(super.listaAgendaBarbeiro(usu2.getId())).getBytes("UTF-8"); 
	}
	
	

	@RequestMapping(value="listarBarbeiroPorDia/{dia}", method=RequestMethod.GET, headers = "Accept=application/json" )
	@ResponseBody
	public String listarBarbeiroTodosStatusPorDia(@PathVariable("dia") String dia) throws Exception {
		
		
		dia = dia.replaceAll("-", "/");
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		
		
		System.out.println("usuario listarBarbeiroPorDia="+ email);
		
		Usuario usu2 = new Usuario();
		
		usu2 = usu.recuperarUsuario(email);
		

		
		System.out.println("usuario getId="+ usu2.getId().toString());
		

		
		
	
		
		return new Gson().toJson(super.listaAgendaBarbeiroTodosPorDia(usu2.getId(), dia)); 
	}
	
	
	  
	
	/*
	@RequestMapping(value="deletar/{codAgenda}", method=RequestMethod.DELETE)
	public  @ResponseBody String deletar (@PathVariable("codAgenda") String codAgenda) throws Exception {
		super.deletar(loadObjeto(Long.parseLong(codAgenda)));
		return "";
	}*/
	
	@RequestMapping(value="deletar/{codAgenda}", method=RequestMethod.POST)
	public  @ResponseBody String deletar (@PathVariable("codAgenda") String codAgenda) throws Exception {
		
		  Agenda agenda = new Agenda();
		  
		  agenda = loadObjeto(Long.parseLong(codAgenda));
		
		  agenda.setStatus("Cancelado");
		  
		  super.salvarOuAtualizar(agenda);
		  
		  
		  
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String email = authentication.getName();
			
			
			System.out.println("usuario deletar="+ email);
			
			Usuario usu3 = new Usuario();
			
			usu3 = usu.recuperarUsuario(email);
		  
		  
		  if (usu3.getPerfil().getId() == 1l) {
		  
		
			 
				// takes input from e-mail form
		        String recipientAddress = agenda.getCliente().getEmail();
		        String subject = "Cancelamento do Atendimento";
		        String message = "Paulo precisou cancelar seu atendimento no dia "+ agenda.getDia() +". \n \n Faça login no sistema para fazer outro agendamento!";
		         
		        // prints debug info
		        System.out.println("To: " + recipientAddress);
		        System.out.println("Subject: " + subject);
		        System.out.println("Message: " + message);
		         
		        // creates a simple e-mail object
		        SimpleMailMessage email2 = new SimpleMailMessage();
		        email2.setTo(recipientAddress);
		        email2.setSubject(subject);
		        email2.setText(message);
		         
		        // sends the e-mail
		        mailSender.send(email2);
		  
		  }
		  
		  
		  
		  
		  
		
		//super.deletar(loadObjeto(Long.parseLong(codAgenda)));
		return "";
	}
	
	
	@RequestMapping(value="atender/{codAgenda}", method=RequestMethod.POST)
	public  @ResponseBody String atender (@PathVariable("codAgenda") String codAgenda) throws Exception {
		
		  Agenda agenda = new Agenda();
		  
		  agenda = loadObjeto(Long.parseLong(codAgenda));
		
		  agenda.setStatus("Atendido");
		  
		  super.salvarOuAtualizar(agenda);
		

		return "";
	}
	
	
	
	
	

	 @RequestMapping(value="salvar", method = RequestMethod.POST, headers = "Accept=application/json")
	 @ResponseBody
	 public ResponseEntity<String>  salvar(@RequestBody Agenda jsonAgenda, String a) throws Exception{
		
		 
	//	 try {
		// Gson gson =  new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
		 //Agenda agenda = gson.fromJson(jsonAgenda, Agenda.class);
		 
			List<String> erros = new ArrayList<String>();
			
			String erros2 = "";
			
			 Gson gson =  new GsonBuilder().create();
			 
			 System.out.println("jsonAgenda ser size = "+jsonAgenda.getServicos().size());
			 
			 
				if (jsonAgenda.getDia() == null){
					

					 

					erros.add("Selecione um Dia <br>");

							
							
						
						
					}
				
	if (jsonAgenda.getHora() == null){
		

		 

	erros.add("Selecione um Hor&aacute;rio <br>");

			
			
		
		
	}
	//if (jsonAgenda.getServicos() == null){
	
	if (jsonAgenda.getServicos().size() == 0){
		
	
			erros.add("Selecione um Servi&ccedil;o <br>");
	

	

			
	
		
	}
	
	if (jsonAgenda.getBarbeiro() == null){
		
	
			erros.add("Selecione um Barbeiro <br>");

		

}
	
	if (!erros.isEmpty()){
		 System.out.println("gson.toJson(erros) = "+gson.toJson(erros));
	return new ResponseEntity<String>(gson.toJson(erros),HttpStatus.BAD_REQUEST);
	}
		 
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	 Usuario u = new Usuario();
	 
	 u.setId(usu.recuperarUsuario( authentication.getName()).getId());
	 
	 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
	 
	 
	 int tamanho = jsonAgenda.getServicos().size();
	 
	 
		Calendar dataMarcada = Calendar.getInstance();
		dataMarcada.setTime((java.util.Date)formatter.parse(jsonAgenda.getDia() + " " + jsonAgenda.getHora()));
	
	for (int idx = 0; idx < tamanho; idx++) {
		
		
		
		
		
		if (tamanho > 1 && idx > 0){
			
			
			dataMarcada.add(Calendar.MINUTE,15);
			
		}
	   
		 
		 
	    Agenda agenda = new Agenda(); 
	    
	   // agenda = jsonAgenda;
	   
	    agenda.setServico( jsonAgenda.getServicos().get(idx) );
	    
	    agenda.setBarbeiro(jsonAgenda.getBarbeiro());
		 agenda.setCliente(u);
		 agenda.setUsuario(u);
		 
		 agenda.setDia(jsonAgenda.getDia());
		 
		 agenda.setHora(sdf.format(dataMarcada.getTime()));
		 
		 agenda.setStatus("Agendado");
		 
		 agenda.setObs(jsonAgenda.getObs());
		 
		 super.salvarOuAtualizar(agenda);
		 
		 
	//	 agenda = null;
		 
	    
	}
	

	 return new ResponseEntity<String>("Agendado com sucesso--------!",HttpStatus.OK);
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	/*ok old
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			

			 System.out.println("jsonAgenda = "+jsonAgenda.getId());
			 
			 System.out.println(" authentication.getName() = "+ authentication.getName());
			 
			 
			 Agenda agenda = jsonAgenda; // new Gson().fromJson(jsonAgenda, Agenda.class);
			 
			 System.out.println(" recuperarUsuario= "+ usu.recuperarUsuario( authentication.getName()).getNome());
			 
			 Usuario u = new Usuario();
			 
			 u.setId(usu.recuperarUsuario( authentication.getName()).getId());

			 System.out.println(" getId. = "+ u.getId());
			 
			 System.out.println(" agenda id. = "+ agenda.getId());
			 
			 agenda.setCliente(u);
			 agenda.setUsuario(u);
			 agenda.setStatus("Agendado");
	 		 
			 super.salvarOuAtualizar(agenda);
			 
			 
			 
			 
			 
			 
			 
			 
			 return new ResponseEntity<String>("Agendado com sucesso--------!",HttpStatus.OK);
			 
			 */
			 
		
		/* }catch(Exception e){
			 return new ResponseEntity<String>( "Erro",HttpStatus.BAD_REQUEST);
			 
		 }*/
		
		 
	 }
	 
	 

	 @RequestMapping(value="salvarAgendaBarbeiro", method = RequestMethod.POST, headers = "Accept=application/json")
	 @ResponseBody
	 public ResponseEntity<String>  salvarAgendaBarbeiro(@RequestBody Agenda jsonAgenda, String a) throws Exception{
		
		 
	//	 try {
		// Gson gson =  new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
		 //Agenda agenda = gson.fromJson(jsonAgenda, Agenda.class);
		 
			List<String> erros = new ArrayList<String>();
			
			String erros2 = "";
			
			 Gson gson =  new GsonBuilder().create();
			 
			 System.out.println("jsonAgenda ser size = "+jsonAgenda.getServicos().size());
			 
			 
				if (jsonAgenda.getDia() == null){
					

					 

					erros.add("Selecione um Dia <br>");

							
							
						
						
					}
				
	if (jsonAgenda.getHora() == null){
		

		 

	erros.add("Selecione um Hor&aacute;rio <br>");

			
			
		
		
	}
	//if (jsonAgenda.getServicos() == null){
	
	if (jsonAgenda.getServicos().size() == 0){
		
	
			erros.add("Selecione um Servi&ccedil;o <br>");
	

	

			
	
		
	}
	
	if (jsonAgenda.getCliente() == null){
		
	
			erros.add("Selecione um Cliente <br>");

		

}
	
	if (!erros.isEmpty()){
		 System.out.println("gson.toJson(erros) = "+gson.toJson(erros));
	return new ResponseEntity<String>( gson.toJson(erros),HttpStatus.BAD_REQUEST);
	}
		 
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	 Usuario u = new Usuario();
	 
	 u.setId(usu.recuperarUsuario( authentication.getName()).getId());
	 
	 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
	 
	 
	 int tamanho = jsonAgenda.getServicos().size();
	 
	 
		Calendar dataMarcada = Calendar.getInstance();
		dataMarcada.setTime((java.util.Date)formatter.parse(jsonAgenda.getDia() + " " + jsonAgenda.getHora()));
	
	for (int idx = 0; idx < tamanho; idx++) {
		
		
		
		
		
		if (tamanho > 1 && idx > 0){
			
			
			dataMarcada.add(Calendar.MINUTE,15);
			
		}
	   
		 
		 
	    Agenda agenda = new Agenda(); 
	    
	   // agenda = jsonAgenda;
	   
	    agenda.setServico( jsonAgenda.getServicos().get(idx) );
	    
	    agenda.setBarbeiro(u);
		 agenda.setCliente(jsonAgenda.getCliente());
		 agenda.setUsuario(u);
		 
		 agenda.setDia(jsonAgenda.getDia());
		 
		 agenda.setHora(sdf.format(dataMarcada.getTime()));
		 
		 agenda.setStatus("Agendado");
		 
		 agenda.setObs(jsonAgenda.getObs());
		 
		 super.salvarOuAtualizar(agenda);
		 
		 
	//	 agenda = null;
		 
	    
	}
	

	 return new ResponseEntity<String>("Agendado com sucesso--------!",HttpStatus.OK);
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	/*ok old
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			

			 System.out.println("jsonAgenda = "+jsonAgenda.getId());
			 
			 System.out.println(" authentication.getName() = "+ authentication.getName());
			 
			 
			 Agenda agenda = jsonAgenda; // new Gson().fromJson(jsonAgenda, Agenda.class);
			 
			 System.out.println(" recuperarUsuario= "+ usu.recuperarUsuario( authentication.getName()).getNome());
			 
			 Usuario u = new Usuario();
			 
			 u.setId(usu.recuperarUsuario( authentication.getName()).getId());

			 System.out.println(" getId. = "+ u.getId());
			 
			 System.out.println(" agenda id. = "+ agenda.getId());
			 
			 agenda.setCliente(u);
			 agenda.setUsuario(u);
			 agenda.setStatus("Agendado");
	 		 
			 super.salvarOuAtualizar(agenda);
			 
			 
			 
			 
			 
			 
			 
			 
			 return new ResponseEntity<String>("Agendado com sucesso--------!",HttpStatus.OK);
			 
			 */
			 
		
		/* }catch(Exception e){
			 return new ResponseEntity<String>( "Erro",HttpStatus.BAD_REQUEST);
			 
		 }*/
		
		 
	 }
	 
	 

	 @RequestMapping(value="buscarAgenda/{codAgenda}", method=RequestMethod.GET)
		public  @ResponseBody String buscarAgenda (@PathVariable("codAgenda") String codAgenda) throws Exception {
			Agenda objeto = super.loadObjeto(Long.parseLong(codAgenda));
			if (objeto == null) {
				return "{}";
			}
			
			 Gson gson =  new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
			 return gson.toJson(objeto);
			 
			//return new Gson().toJson(objeto);
		}
	 
	 
	 @RequestMapping(value="listarHorariosBarbeiros/{codBarbeiro}/{dia}", method=RequestMethod.GET)
		public  @ResponseBody String listarHorariosBarbeiros (@PathVariable("codBarbeiro") String codBarbeiro, @PathVariable("dia") String dia) throws Exception {

	 
		 System.out.println("codBarbeiro = "+codBarbeiro);
		 System.out.println("dia = "+dia);
		 
	// List<Agenda> objeto = lista(Long.parseLong(codBarbeiro), dia);
			
		 //List<Date> objetoListaDeDatas = lista(Long.parseLong(codBarbeiro), dia);
		 
		 dia = dia.replaceAll("-", "/");
		 
		 List<String> objetoListaDeDatas = lista(Long.parseLong(codBarbeiro), dia);
		 
		 List<String> dataEhoraDisponiveis = new ArrayList<String>();
			 

			
			if (objetoListaDeDatas.size() == 0) {
				
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
				
				
				
				Calendar dataEscolhidaTela = Calendar.getInstance();
				dataEscolhidaTela.setTime((java.util.Date)formatter.parse(dia));
				dataEscolhidaTela.set(Calendar.HOUR,9);
				dataEscolhidaTela.set(Calendar.MINUTE,0);
				   
				Calendar dataEscolhidaTelaFim = Calendar.getInstance();
				dataEscolhidaTelaFim.setTime((java.util.Date)formatter.parse(dia));
				dataEscolhidaTelaFim.set(Calendar.HOUR,18);
				dataEscolhidaTelaFim.set(Calendar.MINUTE,15);
					   
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					
				//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					
					
				while(dataEscolhidaTela.getTime().before(dataEscolhidaTelaFim.getTime())  ){
		
					dataEhoraDisponiveis.add(sdf.format(dataEscolhidaTela.getTime()));
					dataEscolhidaTela.add(Calendar.MINUTE,15);
							 
				}
				

			}
			else {
								
				   List<Date> dataEhoraMarcados = new ArrayList<Date>();
				
				   DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	

				   dia = dia.replaceAll("-", "/");
				
				   Calendar dataEscolhidaTela = Calendar.getInstance();
				   dataEscolhidaTela.setTime((java.util.Date)formatter.parse(dia));
				   dataEscolhidaTela.set(Calendar.HOUR,9);
				   dataEscolhidaTela.set(Calendar.MINUTE,0);
				   
				   Calendar dataEscolhidaTelaFim = Calendar.getInstance();
				   dataEscolhidaTelaFim.setTime((java.util.Date)formatter.parse(dia));
				   dataEscolhidaTelaFim.set(Calendar.HOUR,18);
				   dataEscolhidaTelaFim.set(Calendar.MINUTE,15);

				 //  DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				   DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				   
					for (int i = 0; i < objetoListaDeDatas.size(); i++) {
						
						// dataEhoraMarcados.add(df.parse( objetoListaDeDatas.get(i).toString())) ;
						/*
						java.util.Date dataRegistro = null; 
						
					
				
						
						String a = objetoListaDeDatas.get(i);
			
						
						dataRegistro = (java.util.Date) df.parse(a);
						
						*/
						 dataEhoraMarcados.add((java.util.Date) df.parse(objetoListaDeDatas.get(i)) ) ;
					
					}
					
					
				//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					
					while(dataEscolhidaTela.getTime().before(dataEscolhidaTelaFim.getTime())  ){

						System.out.println("data " + dataEscolhidaTela.getTime() +" esta na lista?"+dataEhoraMarcados.contains(dataEscolhidaTela.getTime())); 
							
							
							if (dataEhoraMarcados.contains(dataEscolhidaTela.getTime())==false)
							{
								dataEhoraDisponiveis.add(sdf.format(dataEscolhidaTela.getTime()));
							}
					
						dataEscolhidaTela.add(Calendar.MINUTE,15);	
					 
					}

				}
			
			 
			/*	for (int i = 0; i < dataEhoraDisponiveis.size(); i++) {
					System.out.println(dataEhoraDisponiveis.get(i)); 	
				}*/
			 
			// Gson gson =  new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
			 //Gson gson2 =  new GsonBuilder().setDateFormat("HH:mm").create();
			 Gson gson =  new GsonBuilder().create();
				
			 System.out.println(gson.toJson(dataEhoraDisponiveis)); 
			 
			 return gson.toJson(dataEhoraDisponiveis);

		}
	 
	 
	 
	 @RequestMapping(value="listarHorarios/{dia}", method=RequestMethod.GET)
		public  @ResponseBody String listarHorariosBarbeiroSemId (@PathVariable("dia") String dia) throws Exception {

	 
		 
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String email = authentication.getName();
			
			
			System.out.println("usuario listarBarbeiroPorDia="+ email);
			
			Usuario usu2 = new Usuario();
			
			usu2 = usu.recuperarUsuario(email);
			

			
			System.out.println("usuario getId="+ usu2.getId().toString());
		 
		 
		 System.out.println("codBarbeiro *********= "+usu2.getId());
		 System.out.println("dia = "+dia);
		 
	// List<Agenda> objeto = lista(Long.parseLong(codBarbeiro), dia);
			
		 //List<Date> objetoListaDeDatas = lista(Long.parseLong(codBarbeiro), dia);
		 
		 dia = dia.replaceAll("-", "/");
		 
		 List<String> objetoListaDeDatas = lista(usu2.getId(), dia);
		 
		 List<String> dataEhoraDisponiveis = new ArrayList<String>();
			 

			
			if (objetoListaDeDatas.size() == 0) {
				
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
				
				
				
				Calendar dataEscolhidaTela = Calendar.getInstance();
				dataEscolhidaTela.setTime((java.util.Date)formatter.parse(dia));
				dataEscolhidaTela.set(Calendar.HOUR,9);
				dataEscolhidaTela.set(Calendar.MINUTE,0);
				   
				Calendar dataEscolhidaTelaFim = Calendar.getInstance();
				dataEscolhidaTelaFim.setTime((java.util.Date)formatter.parse(dia));
				dataEscolhidaTelaFim.set(Calendar.HOUR,18);
				dataEscolhidaTelaFim.set(Calendar.MINUTE,15);
					   
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					
				//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					
					
				while(dataEscolhidaTela.getTime().before(dataEscolhidaTelaFim.getTime())  ){
		
					dataEhoraDisponiveis.add(sdf.format(dataEscolhidaTela.getTime()));
					dataEscolhidaTela.add(Calendar.MINUTE,15);
							 
				}
				

			}
			else {
								
				   List<Date> dataEhoraMarcados = new ArrayList<Date>();
				
				   DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	

				   dia = dia.replaceAll("-", "/");
				
				   Calendar dataEscolhidaTela = Calendar.getInstance();
				   dataEscolhidaTela.setTime((java.util.Date)formatter.parse(dia));
				   dataEscolhidaTela.set(Calendar.HOUR,9);
				   dataEscolhidaTela.set(Calendar.MINUTE,0);
				   
				   Calendar dataEscolhidaTelaFim = Calendar.getInstance();
				   dataEscolhidaTelaFim.setTime((java.util.Date)formatter.parse(dia));
				   dataEscolhidaTelaFim.set(Calendar.HOUR,18);
				   dataEscolhidaTelaFim.set(Calendar.MINUTE,15);

				 //  DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				   DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				   
					for (int i = 0; i < objetoListaDeDatas.size(); i++) {
						
						// dataEhoraMarcados.add(df.parse( objetoListaDeDatas.get(i).toString())) ;
						/*
						java.util.Date dataRegistro = null; 
						
					
				
						
						String a = objetoListaDeDatas.get(i);
			
						
						dataRegistro = (java.util.Date) df.parse(a);
						
						*/
						 dataEhoraMarcados.add((java.util.Date) df.parse(objetoListaDeDatas.get(i)) ) ;
					
					}
					
					
				//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					
					while(dataEscolhidaTela.getTime().before(dataEscolhidaTelaFim.getTime())  ){

						System.out.println("data " + dataEscolhidaTela.getTime() +" esta na lista?"+dataEhoraMarcados.contains(dataEscolhidaTela.getTime())); 
							
							
							if (dataEhoraMarcados.contains(dataEscolhidaTela.getTime())==false)
							{
								dataEhoraDisponiveis.add(sdf.format(dataEscolhidaTela.getTime()));
							}
					
						dataEscolhidaTela.add(Calendar.MINUTE,15);	
					 
					}

				}
			
			 
			/*	for (int i = 0; i < dataEhoraDisponiveis.size(); i++) {
					System.out.println(dataEhoraDisponiveis.get(i)); 	
				}*/
			 
			// Gson gson =  new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
			 //Gson gson2 =  new GsonBuilder().setDateFormat("HH:mm").create();
			 Gson gson =  new GsonBuilder().create();
				
			 System.out.println(gson.toJson(dataEhoraDisponiveis)); 
			 
			 return gson.toJson(dataEhoraDisponiveis);

		}
	 
	 
	 public List<String> lista(Long codBarbeiro, String dia) throws Exception {

			
		 String sql = " select  dia|| ' ' ||hora from Agenda "
					+ " where barbeiro_id = :codBarbeiro and dia = :dia and status <> 'Cancelado' ";
		 
			  Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			  query.setParameter("codBarbeiro",codBarbeiro);
			  query.setParameter("dia",dia);
			  
			//  System.out.println("query = "+query.toString());
			  
			//  System.out.println("query re = "+query.list().toString());
			  
			//  System.out.println("query SIZE = "+query.list().size());
		 
		 List<String> lista = query.list();
		 
		 
		 
	return lista;
		}
	 
	 public List<Agenda> listaold(Long codBarbeiro, String dia) throws Exception {
		 
			String sql = " from Agenda"
					+ " where barbeiro_id= :codBarbeiro and status = 'Liberado' ";
		
		 
			  Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			  query.setParameter("codBarbeiro",codBarbeiro);
			  
			//  System.out.println("query = "+query.toString());
			  
			//  System.out.println("query re = "+query.list().toString());
			  
			//  System.out.println("query SIZE = "+query.list().size());
		 
		 List<Agenda> lista = query.list();
		 
		 
		 
	return lista;
		}
	 
	 
	 
		@RequestMapping(value="abrir", method=RequestMethod.GET)
		public  @ResponseBody String abrir (@PathVariable("mes") String mes) throws Exception {
			
	
			
			List<Date> date = new ArrayList<Date>();
			date.add(new Date());
			
			
			return "";
		
			/*
			List<String> permissoes = new ArrayList<String>();
			
			Iterator<GrantedAuthority> iterator = (Iterator<GrantedAuthority>) authentication.getAuthorities().iterator();
			
			while (iterator.hasNext()){
				permissoes.add(iterator.next().getAuthority());
			}
			
			map.put("permissoes", permissoes);
			
			return new Gson().toJson(map);*/
		}
	 
	/**
	 * Salva ou atualiza o cliente
	 * @param jsonCliente
	 * @return ResponseEntity
	 * @throws Exception
	 */
	/* @RequestMapping(value="salvar", method= RequestMethod.POST)
	 @ResponseBody
	 public ResponseEntity salvar (@RequestBody String jsonCliente) throws Exception {
		 Cliente cliente = new Gson().fromJson(jsonCliente, Cliente.class);
		 
		 if (cliente != null && cliente.getAtivo() == null){
			 cliente.setAtivo(false);
		 }
		 
		 super.salvarOuAtualizar(cliente);
		 return new ResponseEntity(HttpStatus.CREATED);
		 
	 }
	*/

	
	 /**
	  * Retorna a lista de clientes cadastrados
	  * @return JSON String de Clientes
	  * @throws Exception
	  */
	/*@RequestMapping(value="listar/{numeroPagina}", method=RequestMethod.GET, headers = "Accept=application/json") 
	@ResponseBody
	public String listar(@PathVariable("numeroPagina") String numeroPagina) throws Exception {
		return new Gson().toJson(super.consultaPaginada(numeroPagina)); 
	}
	
	
	@RequestMapping(value="totalPagina", method=RequestMethod.GET, headers = "Accept=application/json") 
	@ResponseBody
	public String totalPagina() throws Exception {
		return ""+super.quantidadePagina(); 
	}
	 */
	/**
	 * Delete o cliente informado
	 * @param codCliente
	 * @return String vazia como resposta
	 * @throws Exception
	 */
	/*@RequestMapping(value="deletar/{codCliente}", method=RequestMethod.DELETE)
	public  @ResponseBody String deletar (@PathVariable("codCliente") String codCliente) throws Exception {
		super.deletar(loadObjeto(Long.parseLong(codCliente)));
		return "";
	}
	*/
	
	/**
	 * Consulta e retorna o cliente com o codigo informado
	 * @param codCliente
	 * @return JSON cliente pesquisado
	 * @throws Exception
	 */
	/*@RequestMapping(value="buscarcliente/{codCliente}", method=RequestMethod.GET)
	public  @ResponseBody String buscarCliente (@PathVariable("codCliente") String codCliente) throws Exception {
		Cliente objeto = super.loadObjeto(Long.parseLong(codCliente));
		if (objeto == null) {
			return "{}";
		}
		return new Gson().toJson(objeto);
	}
	*/
	
	/**
	 * Consulta e retorna o cliente com o nome  informado
	 * @param nomeCliente
	 * @return JSON cliente pesquisado
	 * @throws Exception
	 */
	/*@RequestMapping(value="buscarnome/{nomeCliente}", method=RequestMethod.GET)
	public  @ResponseBody String buscarNome (@PathVariable("nomeCliente") String nomeCliente) throws Exception {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = super.listaLikeExpression("nome", nomeCliente);

		if (clientes == null || clientes.isEmpty() ) {
			return "{}";
		}
		
		return new Gson().toJson(clientes);
	}
*/
	 

		@RequestMapping(value="autenticar", method=RequestMethod.GET)
		public  @ResponseBody String autenticar () throws Exception {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("permissao", true);
			map.put("user", authentication.getName());
			
			List<String> permissoes = new ArrayList<String>();
			
			Iterator<GrantedAuthority> iterator = (Iterator<GrantedAuthority>) authentication.getAuthorities().iterator();
			
			while (iterator.hasNext()){
				permissoes.add(iterator.next().getAuthority());
			}
			
			map.put("permissoes", permissoes);
			
			return new Gson().toJson(map);
		}
		
		
		@RequestMapping(value="sair", method = RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<String> sa (HttpServletRequest request, HttpServletResponse response) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			   System.out.println("sair: ");
			
			if (auth != null){    
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			 return new ResponseEntity<String>("",HttpStatus.OK);

				
		}

		
		@RequestMapping(value="trocar", method = RequestMethod.POST, headers = "Accept=application/json")
		@ResponseBody
		public ResponseEntity<String> trocar(@RequestBody String senha)throws Exception {

			if( !senha.isEmpty() ) { 
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String email = authentication.getName();
			
			System.out.println("usuario listar="+ email);
			

			
			Usuario usu2 = new Usuario();
			
			usu2 = usu.recuperarUsuario(email);
			
			usu2.setSenha(senha);
			
			usu.salvarOuAtualizar(usu2);
			
			return new ResponseEntity<String>("Senha Alterada com sucesso!",HttpStatus.OK);
			
			}
			
			else
				
			{
				return new ResponseEntity<String>("Senhas em branco!",HttpStatus.BAD_REQUEST);
				
			}
			

		}

}
