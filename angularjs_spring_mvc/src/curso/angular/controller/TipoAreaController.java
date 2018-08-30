package curso.angular.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import curso.angular.model.TipoArea;

@Controller
@RequestMapping(value = "/tipoarea")
public class TipoAreaController extends DaoImplementacao<TipoArea> implements
	DaoInterface<TipoArea> {

	public TipoAreaController(Class<TipoArea> persistenceClass) {
		super(persistenceClass);
	}
	


	@RequestMapping(value="listar", method=RequestMethod.GET, headers = "Accept=application/json" )
	@ResponseBody
	public byte[] listar() throws Exception {
		return new Gson().toJson(super.lista("status","Ativo")).getBytes("UTF-8"); 
	}		  
	 
	@RequestMapping(value="recuperar/{codTipoArea}", method=RequestMethod.GET , headers = "Accept=application/json")
	public  @ResponseBody String recuperarArea(@PathVariable("codTipoArea") String codTipoArea) throws Exception {
		TipoArea objeto = super.loadObjeto(Long.parseLong(codTipoArea));
		if (objeto == null) {
			return "{}";
		}
		return new Gson().toJson(objeto);
	}	
	
	@RequestMapping(value="salvar", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> adicionar(@RequestBody TipoArea tipoArea)throws Exception {
		
		List<String> erros = new ArrayList<String>();		
		Gson gson =  new GsonBuilder().create();			 
		if (tipoArea.getNome() == null){
				erros.add("Informe a nome do tipo da �rea");
		}	
		if (tipoArea.getDescricao() == null){
				erros.add("Informe a descri��o do tipo da �rea ");
		}	
		
		if (!erros.isEmpty()){	
			 System.out.println("gson.toJson(erros) = "+gson.toJson(erros));
			return new ResponseEntity<String>( gson.toJson(erros),HttpStatus.BAD_REQUEST);
			
		}else{		
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String usuario = authentication.getName();
			
			Calendar data = Calendar.getInstance();
			
			if (tipoArea.getId() != null){	
				tipoArea.setData_alteracao(formatter.format(data.getTime()));
				tipoArea.setUsuario_alteracao(usuario);			
				super.salvarOuAtualizar(tipoArea);
				return new ResponseEntity<String>("Alterado com sucesso!",HttpStatus.OK);
				
			}else{			
				tipoArea.setData_inclusao(formatter.format(data.getTime()));			
				tipoArea.setUsuario_inclusao(usuario);			
				//o que seria isso???
				tipoArea.setStatus("Ativo");			
				super.salvarOuAtualizar(tipoArea);			
				return new ResponseEntity<String>("Cadastrado com sucesso!",HttpStatus.OK);			
			}
		}
	}	
	
	@RequestMapping(value="excluir/{codTipoArea}", method=RequestMethod.POST)
	public  @ResponseBody ResponseEntity<String> excluirArea(@PathVariable("codTipoArea") String codTipoArea) throws Exception {
	
			TipoArea tipoArea = new TipoArea();
			tipoArea = super.loadObjeto(Long.parseLong(codTipoArea));
			
			//o que seria isso????
			tipoArea.setStatus("Deletado");
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
			Calendar dataExclusao = Calendar.getInstance();
			tipoArea.setData_exclusao(formatter.format(dataExclusao.getTime()));
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String usuario_exclusao = authentication.getName();
			tipoArea.setUsuario_exclusao(usuario_exclusao);
	
			super.salvarOuAtualizar(tipoArea);
			return new ResponseEntity<String>("Exclu�do com sucesso!",HttpStatus.OK);
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