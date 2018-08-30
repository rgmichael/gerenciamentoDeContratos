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
import curso.angular.model.Fabricante;


@Controller
@RequestMapping(value = "/fabricante")
public class FabricanteController extends DaoImplementacao<Fabricante> implements
	DaoInterface<Fabricante> {

public FabricanteController(Class<Fabricante> persistenceClass) {
	super(persistenceClass);
}

/**
 * Faz o carregamento da lista de fabricantes
 * @return List<Estados> 
 * @throws Exception
 */
@RequestMapping(value = "listar", method = RequestMethod.GET, headers = "Accept=application/json")
@ResponseBody
public byte[] listar() throws Exception {
	
	return new Gson().toJson(super.lista()).getBytes("UTF-8");
}

@RequestMapping(value="recuperar/{codFabricante}", method=RequestMethod.GET , headers = "Accept=application/json")
public  @ResponseBody String recuperarFabricante(@PathVariable("codFabricante") String codFabricante) throws Exception {
	Fabricante objeto = super.loadObjeto(Long.parseLong(codFabricante));
	if (objeto == null) {
		return "{}";
	}
	return new Gson().toJson(objeto);
}

@RequestMapping(value="salvar", method = RequestMethod.POST, headers = "Accept=application/json")
@ResponseBody
public ResponseEntity<String> adicionar(@RequestBody Fabricante fabricante)throws Exception {
	List<String> erros = new ArrayList<String>();		
	Gson gson =  new GsonBuilder().create();			 
	if (fabricante.getNome() == null){
			erros.add("Informe a nome");
	}	
	if (fabricante.getDescricao() == null){
			erros.add("Informe o descrição");
	}
	if (!erros.isEmpty()){	
		 System.out.println("gson.toJson(erros) = "+gson.toJson(erros));
		return new ResponseEntity<String>( gson.toJson(erros),HttpStatus.BAD_REQUEST);		
	}else{		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuario = authentication.getName();		
		Calendar data = Calendar.getInstance();		
		if (fabricante.getId() != null){
			fabricante.setData_alteracao(formatter.format(data.getTime()));
			fabricante.setUsuario_alteracao(usuario);			
			super.salvarOuAtualizar(fabricante);
			return new ResponseEntity<String>("Alterado com sucesso!",HttpStatus.OK);			
		}else{			
			fabricante.setData_inclusao(formatter.format(data.getTime()));			
			fabricante.setUsuario_inclusao(usuario);			
			//fabricante.setStatus("Ativo");			
			super.salvarOuAtualizar(fabricante);			
			return new ResponseEntity<String>("Cadastrado com sucesso!",HttpStatus.OK);			
		}
	}
}

@RequestMapping(value="excluir/{codFabricante}", method=RequestMethod.POST)
public  @ResponseBody ResponseEntity<String> excluirFabricante(@PathVariable("codFabricante") String codFabricante) throws Exception {

		Fabricante fabricante = new Fabricante();
		fabricante = super.loadObjeto(Long.parseLong(codFabricante));		
		//fabricante.setStatus("Deletado");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
		Calendar dataExclusao = Calendar.getInstance();
		fabricante.setData_exclusao(formatter.format(dataExclusao.getTime()));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usuario_exclusao = authentication.getName();
		fabricante.setUsuario_exclusao(usuario_exclusao);

		//super.salvarOuAtualizar(fabricante);
		super.deletar(fabricante);

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