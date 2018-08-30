package curso.angular.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
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
import curso.angular.model.Perfil;
import curso.angular.model.Equipamento;



@Controller
@RequestMapping(value = "/equipamento")
public class EquipamentoController extends DaoImplementacao<Equipamento> implements
	DaoInterface<Equipamento> {

public EquipamentoController(Class<Equipamento> persistenceClass) {
	super(persistenceClass);
}


@RequestMapping(value="listar", method=RequestMethod.GET, headers = "Accept=application/json" )
@ResponseBody
public byte[] listar() throws Exception {
	return new Gson().toJson(super.lista("status","Ativo")).getBytes("UTF-8"); 
}
	  
 
@RequestMapping(value="recuperar/{codEquipamento}", method=RequestMethod.GET , headers = "Accept=application/json")
public  @ResponseBody String recuperarEquipamento(@PathVariable("codEquipamento") String codEquipamento) throws Exception {
	Equipamento objeto = super.loadObjeto(Long.parseLong(codEquipamento));
	if (objeto == null) {
		return "{}";
	}
	return new Gson().toJson(objeto);
}


@RequestMapping(value="salvar", method = RequestMethod.POST, headers = "Accept=application/json")
@ResponseBody
public ResponseEntity<String> adicionar(@RequestBody Equipamento equipamento)throws Exception {
	

	List<String> erros = new ArrayList<String>();
		
	Gson gson =  new GsonBuilder().create();
			 
	if (equipamento.getTag() == null){
			erros.add("Informe a TAG");
	}
	
	if (equipamento.getFabricante() == null){
			erros.add("Informe do Fabricante");
	}
	
	if (equipamento.getEquipamento() == null){
		erros.add("Informe o Equipamento");
	}
	
	
	if (equipamento.getModelo()== null){
		erros.add("Informe o Modelo");
	}
	

	
	if (!erros.isEmpty()){
	
		 System.out.println("gson.toJson(erros) = "+gson.toJson(erros));
		return new ResponseEntity<String>( gson.toJson(erros),HttpStatus.BAD_REQUEST);
		
	}else{
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuario = authentication.getName();
		
		Calendar data = Calendar.getInstance();
		
		if (equipamento.getId() != null){	

			equipamento.setData_alteracao(formatter.format(data.getTime()));

			equipamento.setUsuario_alteracao(usuario);
			
			super.salvarOuAtualizar(equipamento);

			return new ResponseEntity<String>("Alterado com sucesso!",HttpStatus.OK);
			
		}else{
			
			equipamento.setData_inclusao(formatter.format(data.getTime()));
			
			equipamento.setUsuario_inclusao(usuario);
			
			equipamento.setStatus("Ativo");
			
			super.salvarOuAtualizar(equipamento);
			
			return new ResponseEntity<String>("Cadastrado com sucesso!",HttpStatus.OK);
			
		}
		

	}	
	
}


@RequestMapping(value="excluir/{codEquipamento}", method=RequestMethod.POST)
public  @ResponseBody ResponseEntity<String> excluirEquipamento(@PathVariable("codEquipamento") String codEquipamento) throws Exception {

		Equipamento equipamento = new Equipamento();

		equipamento = super.loadObjeto(Long.parseLong(codEquipamento));
		
		equipamento.setStatus("Deletado");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
		Calendar dataExclusao = Calendar.getInstance();
		equipamento.setData_exclusao(formatter.format(dataExclusao.getTime()));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuario_exclusao = authentication.getName();
		equipamento.setUsuario_exclusao(usuario_exclusao);

		super.salvarOuAtualizar(equipamento);

		return new ResponseEntity<String>("Excluído com sucesso!",HttpStatus.OK);

}

@RequestMapping(value="sair", method = RequestMethod.GET)
@ResponseBody
public ResponseEntity<String> sairSistema(HttpServletRequest request, HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	   System.out.println("sair 2: ");
	
	if (auth != null){    
		new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	 return new ResponseEntity<String>("",HttpStatus.OK);

		
}

}