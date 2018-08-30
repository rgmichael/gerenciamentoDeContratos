package curso.angular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import curso.angular.dao.DaoImplementacao;
import curso.angular.dao.DaoInterface;
import curso.angular.model.Servico;


@Controller
@RequestMapping(value = "/servico")
public class ServicoController extends DaoImplementacao<Servico> implements
	DaoInterface<Servico> {

public ServicoController(Class<Servico> persistenceClass) {
	super(persistenceClass);
}

/**
 * Faz o carregamento da lista de barbeiros
 * @return List<Estados> 
 * @throws Exception
 */
@RequestMapping(value = "listar", method = RequestMethod.GET, headers = "Accept=application/json")
@ResponseBody
public byte[] listar() throws Exception {
	
	System.out.println("serviços="+ super.lista().get(1).getDescricao());
	return new Gson().toJson(super.lista()).getBytes("UTF-8");
}


}