package curso.angular.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import curso.angular.dao.DaoImplementacao;
import curso.angular.dao.DaoInterface;
import curso.angular.model.Usuario;



@Controller
@RequestMapping(value = "/barbeiros")
public class BarbeiroController extends DaoImplementacao<Usuario> implements
	DaoInterface<Usuario> {

public BarbeiroController(Class<Usuario> persistenceClass) {
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
	System.out.println("barbeiro listar2=");
	return new Gson().toJson(lista()).getBytes("UTF-8");
}

/**
 * Faz o carregamento das cidades de acordo com o estado
 * @param codigoEstado
 * @return List<Cidades> 
 * @throws Exception
 */
public List<Usuario> lista() throws Exception {
	Criteria criteria = getSessionFactory().getCurrentSession()
			.createCriteria(getPersistenceClass());
	criteria.add(Restrictions.eq("perfil.id", Long.parseLong("1")));
	return criteria.list();
}

}