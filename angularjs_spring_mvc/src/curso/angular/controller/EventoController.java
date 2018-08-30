package curso.angular.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import curso.angular.dao.DaoImplementacao;
import curso.angular.dao.DaoInterface;
import curso.angular.model.Evento;
import curso.angular.model.EventoAux;


@Controller
@RequestMapping(value = "/evento")
public class EventoController extends DaoImplementacao<Evento> implements
	DaoInterface<Evento> {

public EventoController(Class<Evento> persistenceClass) {
	super(persistenceClass);
}
/*
@RequestMapping(value = "/getEventos.json", method = RequestMethod.GET)	
public @ResponseBody List<EventosEntity> GetEventos(){

	List<EventosEntity> eventos = new ArrayList<EventosEntity>();
			
	String mesAtual = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+ 1);
	
	if(mesAtual.length() <2)
		mesAtual = "0" + mesAtual;
	
	String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			 
	/*ADICIONANDO OS EVENTOS
	eventos.add(new EventosEntity("Estudar Linux",     anoAtual+"-"+mesAtual+"-02T12:00:00",anoAtual+"-"+mesAtual+"-02T13:30:00", null));
	eventos.add(new EventosEntity("Estudar Java", 	   anoAtual+"-"+mesAtual+"-02T14:00:00",anoAtual+"-"+mesAtual+"-02T14:30:00", null));
	eventos.add(new EventosEntity("Estudar C#", 	   anoAtual+"-"+mesAtual+"-02T15:00:00",anoAtual+"-"+mesAtual+"-02T15:30:00", null));
	eventos.add(new EventosEntity("Estudar SOA Suite", anoAtual+"-"+mesAtual+"-02T16:00:00",anoAtual+"-"+mesAtual+"-02T17:30:00", null));
	eventos.add(new EventosEntity("Estudar Jquery",    anoAtual+"-"+mesAtual+"-02T19:00:00",anoAtual+"-"+mesAtual+"-02T20:30:00", null));		
	eventos.add(new EventosEntity("Correr",     	   anoAtual+"-"+mesAtual+"-03T13:00:00",anoAtual+"-"+mesAtual+"-03T13:30:00", null));
	eventos.add(new EventosEntity("Reunião",	       anoAtual+"-"+mesAtual+"-05T12:00:00",anoAtual+"-"+mesAtual+"-05T13:30:00", null));		
	eventos.add(new EventosEntity("Dois dias de evento", anoAtual+"-"+mesAtual+"-07T12:00:00",anoAtual+"-"+mesAtual+"-08T12:00:00", null));
	
	eventos.add(new EventosEntity("Publicar Artigo",   anoAtual+"-"+mesAtual+"-10T12:00:00",anoAtual+"-"+mesAtual+"-10T13:30:00", null));
	eventos.add(new EventosEntity("Reunião",	       anoAtual+"-"+mesAtual+"-10T15:00:00",anoAtual+"-"+mesAtual+"-10T18:30:00", null));		
	
	eventos.add(new EventosEntity("Festa",  		   anoAtual+"-"+mesAtual+"-13T12:00:00",anoAtual+"-"+mesAtual+"-13T13:30:00", null));
	eventos.add(new EventosEntity("Festa 2",	       anoAtual+"-"+mesAtual+"-13T15:00:00",anoAtual+"-"+mesAtual+"-13T18:30:00", null));		
	eventos.add(new EventosEntity("Curso de Inglês",   anoAtual+"-"+mesAtual+"-15",null, null));				
	eventos.add(new EventosEntity("Blog Cícero",       anoAtual+"-"+mesAtual+"-23",null, "http://www.ciceroednilson.com.br"));
			
	return eventos;
	
}
*/
@RequestMapping(value = "/listar", method = RequestMethod.GET)	
public @ResponseBody List<EventoAux> GetEventos2() throws Exception {
	
	List<Evento> eventos = super.lista();
	
	List<EventoAux> eventoslist = new ArrayList<EventoAux>();
	

	

    for(int i = 0 ; i < eventos.size(); i++){
    	
    	EventoAux eventosAux = new EventoAux();
    	
    	eventosAux.setId(eventos.get(i).getId());
    	eventosAux.setTitle(eventos.get(i).getTitle());
    	eventosAux.setDescricao(eventos.get(i).getDescricao());
    	eventosAux.setColor(eventos.get(i).getCor());
    	eventosAux.setStart(eventos.get(i).getStart());
    	eventosAux.setEnd(eventos.get(i).getEnd_time());
    	
    	eventosAux.setUsuario_inclusao(eventos.get(i).getUsuario_inclusao());
    	eventosAux.setData_inclusao(eventos.get(i).getData_inclusao());
    	eventosAux.setUsuario_alteracao(eventos.get(i).getUsuario_alteracao());
    	eventosAux.setData_alteracao(eventos.get(i).getData_alteracao());
    	
    	eventoslist.add(eventosAux);
    

    	 
 }
	return eventoslist;
	/*Evento ev = new Evento();

	
	ev.setTitle("Estudar Java");
	ev.setStart("2018-04-02T14:00:00"); 
	ev.setEnd("2018-04-02T16:00:00");
	ev.setCor("red");
	ev.setDescricao("teste");
	
	eventos.add(ev);
	
	String a = new Gson().toJson(super.lista());
	
*/
	
	//return a;
	
	/*String str = "";
	List<Evento> eventos = new ArrayList<Evento>();
	try {
		eventos = super.lista();
		
		str = eventos.toString();
		
		str.replaceAll("end_time", "end");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return str;

/*
	Iterator itr = 	eventos.iterator();
	while(itr.hasNext){
	String a = itr.next();
	String b = "Y";

	if(a.contains(b)){
	String c = b.replace("A");
	System.out.println(c);

	}
	else{
	System.out.println(b);

	}
	
	return new Gson().toJson(super.lista("status","Ativo")).getBytes("UTF-8"); 
			
	/*
	 * 
	 * List<Evento> eventos = new ArrayList<Evento>();
	 * String mesAtual = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+ 1);
	 */
/*	
	if(mesAtual.length() <2)
		mesAtual = "0" + mesAtual;
	
	String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			
	/*ADICIONANDO OS EVENTOS
	
	Evento ev = new Evento();

	
	ev.setTitle("Estudar Java");
	ev.setStart("2018-04-02T14:00:00"); 
	ev.setEnd("2018-04-02T16:00:00");
	ev.setCor("red");
	ev.setDescricao("teste");
	
	eventos.add(ev);
	
	
	
	//eventos.add(new EventosEntity("Estudar Linux",     anoAtual+"-"+mesAtual+"-02T12:00:00",anoAtual+"-"+mesAtual+"-02T13:30:00", null));
	
	//retorno ok: [{"id":null,"title":"Estudar Java","start":"2018-04-02T14:00:00","end":"2018-04-02T16:00:00","url":null}]
	return eventos;
	*/
}



@RequestMapping(value = "/teste", method = RequestMethod.GET)	
public @ResponseBody List<EventoAux> GetEventos3() throws Exception {
	
	List<EventoAux> eventoslist = new ArrayList<EventoAux>();
	
	EventoAux ev = new EventoAux();

	ev.setId(1L); 
	ev.setTitle("Estudar Java");
	ev.setStart("2018-04-14 14:00:00"); 
	ev.setEnd("2018-04-14 18:30:00");
	ev.setColor("red");
	ev.setDescricao("teste");
	
	eventoslist.add(ev);
	
	return eventoslist;//"[{"id":15,"title":"hh","start":"2018-04-04 14:00:00","end":"2018-04-04 17:30:00","descricao":"h","cor":"#FFD700"}]";

}

//@RequestMapping(value="/salvar", method = RequestMethod.POST, headers = "Accept=application/json")
@RequestMapping(value="/salvar", method = RequestMethod.POST)	
public ResponseEntity<String> adicionar(@RequestParam  String evento)throws Exception {
	
	System.out.println("evento cadastrar="+ evento);
	
	Evento eventoObj = new Gson().fromJson(evento, Evento.class);
	
	Gson gson =  new GsonBuilder().create();
	
	List<String> erros = new ArrayList<String>();
	
	
	if (validarDatas(eventoObj.getStart(), eventoObj.getEnd_time(), eventoObj.getId())){

		erros.add("J&aacute; Existe Evento Nesse Per&iacute;odo!");
		
		}
	
	if(erros.size() > 0)
	{
	
		System.out.println("erro cadastro="+gson.toJson(erros)); 
		
	return new ResponseEntity<String>(gson.toJson(erros),HttpStatus.BAD_REQUEST);
	
	}
	else{
		
		
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuario = authentication.getName();
		
		Calendar data = Calendar.getInstance();
		
		if (eventoObj.getId() != null){	

			eventoObj.setData_alteracao(formatter.format(data.getTime()));

			eventoObj.setUsuario_alteracao(usuario);
			
			super.salvarOuAtualizar(eventoObj);

			return new ResponseEntity<String>("Alterado com sucesso!",HttpStatus.OK);
			
		}else{
			
			eventoObj.setData_inclusao(formatter.format(data.getTime()));
			
			eventoObj.setUsuario_inclusao(usuario);
			
			super.salvarOuAtualizar(eventoObj);
			
			return new ResponseEntity<String>("Cadastrado com sucesso!",HttpStatus.OK);
			
		}

	}
	
	
	
	
	/* Gson gson =  new GsonBuilder().create();
	 
	List<String> erros = new ArrayList<String>();
	 
		if (eventoObj.getTitle() == null){

			erros.add("1,");
			
			}
		
		return new ResponseEntity<String>(gson.toJson(erros),HttpStatus.BAD_REQUEST);
	*/
	/* ok
	
	Gson gson = new Gson();
	//String jsonInString = "{\"userId\":\"1\",\"userName\":\"Yasir\"}";
	Evento eventoObj = gson.fromJson(evento, Evento.class);
	
	super.salvarOuAtualizar(eventoObj);
	
	return new ResponseEntity<String>("Cadastrado com sucesso!",HttpStatus.OK);
	
	
	
	
*/
	
	

	
	
}


//@RequestMapping(value="/salvar", method = RequestMethod.POST, headers = "Accept=application/json")
@RequestMapping(value="/excluir", method = RequestMethod.POST)	
public ResponseEntity<String> excluir(@RequestParam  String evento)throws Exception {
	
	System.out.println("evento exluir="+ evento);
			
	Gson gson = new Gson();

	Evento eventoObj = gson.fromJson(evento, Evento.class);
	
	super.deletar(eventoObj);
	
	return new ResponseEntity<String>("Excluído com sucesso!",HttpStatus.OK);
	

	
}
/* old ok
@RequestMapping(value="listarHorarios/{dataEvento}", method=RequestMethod.GET)
	public  @ResponseBody String listarHorariosLivres (@PathVariable("dataEvento") String dia) throws Exception {

	

	System.out.println("evento listarHorariosLivres="+ dia);
	// dia = dia.replaceAll("-", "/");
	 
	 List<String> objetoListaDeDatas = listaHorario("start",dia);
	 
	 List<String> dataEhoraDisponiveis = new ArrayList<String>();
		 

		// dia sem nenhum horário ocupado
		if (objetoListaDeDatas.size() == 0) {
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
			
			Calendar dataEscolhidaTela = Calendar.getInstance();
			dataEscolhidaTela.setTime((java.util.Date)formatter.parse(dia));
			dataEscolhidaTela.set(Calendar.HOUR,8);
			dataEscolhidaTela.set(Calendar.MINUTE,0);
			   
			Calendar dataEscolhidaTelaFim = Calendar.getInstance();
			dataEscolhidaTelaFim.setTime((java.util.Date)formatter.parse(dia));
			dataEscolhidaTelaFim.set(Calendar.HOUR,19);
			dataEscolhidaTelaFim.set(Calendar.MINUTE,0);
				   

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				
				
			while(dataEscolhidaTela.getTime().before(dataEscolhidaTelaFim.getTime())  ){
	
				dataEhoraDisponiveis.add(sdf.format(dataEscolhidaTela.getTime()));
				dataEscolhidaTela.add(Calendar.MINUTE,15);// de 15 em 15 minutos
						 
			}
			

		}
		else {
							
			   List<Date> dataEhoraMarcados = new ArrayList<Date>();
			
			   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
			
			   Calendar dataEscolhidaTela = Calendar.getInstance();
			   dataEscolhidaTela.setTime((java.util.Date)formatter.parse(dia));
			   dataEscolhidaTela.set(Calendar.HOUR,8);
			   dataEscolhidaTela.set(Calendar.MINUTE,0);
			   
			   Calendar dataEscolhidaTelaFim = Calendar.getInstance();
			   dataEscolhidaTelaFim.setTime((java.util.Date)formatter.parse(dia));
			   dataEscolhidaTelaFim.set(Calendar.HOUR,19);
			   dataEscolhidaTelaFim.set(Calendar.MINUTE,0);

			   DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			   
				for (int i = 0; i < objetoListaDeDatas.size(); i++) {
					
					 dataEhoraMarcados.add((java.util.Date) df.parse(objetoListaDeDatas.get(i)) ) ;
				
				}
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				
				while(dataEscolhidaTela.getTime().before(dataEscolhidaTelaFim.getTime())  ){

				//	System.out.println("data " + dataEscolhidaTela.getTime() +" esta na lista?"+dataEhoraMarcados.contains(dataEscolhidaTela.getTime())); 
						
						
						if (dataEhoraMarcados.contains(dataEscolhidaTela.getTime())==false)
						{
							dataEhoraDisponiveis.add(sdf.format(dataEscolhidaTela.getTime()));
						}
				
					dataEscolhidaTela.add(Calendar.MINUTE,15);	// de 15 em 15 minutos
				 
				}

			}
		
		 

		 Gson gson =  new GsonBuilder().create();
			
		 System.out.println(gson.toJson(dataEhoraDisponiveis)); 
		 
		 return gson.toJson(dataEhoraDisponiveis);

	}


public List<String> listaHorario(String campo, String dia) throws Exception {

		
	 String sql = " select "+ campo +" from Evento "
				+ " where "+ campo +" like '%"+dia+"%' ";
	 
	 System.out.println("sql="+sql); 
	 
	 Query query = getSessionFactory().getCurrentSession().createQuery(sql);

	 
	 List<String> lista = query.list();
	 
	 
	 
return lista;
	}


*/



public boolean validarDatas(String ini, String fim, Long id) throws Exception {

	boolean ret = false;
	
	// mysql
	String sql = " select id from Evento "	
	+" where 1=1 "
	+" and    DATE_FORMAT(end_time, '%Y-%m-%d %H:%i') > DATE_FORMAT(:ini, '%Y-%m-%d %H:%i') "
    +" and    DATE_FORMAT(start, '%Y-%m-%d %H:%i')    < DATE_FORMAT(:fim, '%Y-%m-%d %H:%i') ";
	
	//query para hsqldb
	/*+" and    to_date(end_time, 'YYYY-MM-DD HH:MI') > to_date(:ini, 'YYYY-MM-DD HH:MI') "
    +" and    to_date(start, 'YYYY-MM-DD HH:MI')    < to_date(:fim, 'YYYY-MM-DD HH:MI') ";
*/
	System.out.println("sql 4="+sql); 
	 
	Query query = getSessionFactory().getCurrentSession().createQuery(sql);
	 
	  query.setParameter("ini",ini);
	  query.setParameter("fim",fim);

	 
	 List<Long> ids = query.list();
	 
	 if (ids.size() > 0 ){ // tem ao menos 1 evento
		 
		 
		 if (ids.size() == 1 ){ // tem 1 evento
		 
			  if (ids.get(0) == id){
				  ret = false; // mesmo evento, pode alterar
			  }
			  else{
				  ret = true;  // outro evento, n pode alterar
			  }
	     }
		 else //mais de 1 evento, n pode alterar pq pega o horario do outro
		 {
			  ret = true;
			 
		 }
		
	 }else // n encontrou nada no banco
	 {
		 
		 ret = false;
	 }
	 
	 return ret;

}

}