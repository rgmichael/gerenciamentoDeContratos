package curso.angular.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
import curso.angular.model.Usuario;



@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController extends DaoImplementacao<Usuario> implements
	DaoInterface<Usuario> {
	
	
	@Autowired
	private JavaMailSender mailSender;

public UsuarioController(Class<Usuario> persistenceClass) {
	super(persistenceClass);
}

 

@RequestMapping(value="listar", method=RequestMethod.GET, headers = "Accept=application/json" )
@ResponseBody
public byte[] listar() throws Exception {
	 System.out.println("usuario listar = ");
	return new Gson().toJson(super.lista()).getBytes("UTF-8"); 
}



@RequestMapping(value="recuperar/{codUsuario}", method=RequestMethod.GET , headers = "Accept=application/json")
public  @ResponseBody String recuperarEquipamento(@PathVariable("codUsuario") String codUsuario) throws Exception {
	Usuario objeto = super.loadObjeto(Long.parseLong(codUsuario));
	if (objeto == null) {
		return "{}";
	}
	return new Gson().toJson(objeto);
}


public Usuario recuperarUsuario(String email) throws Exception {
	 System.out.println("email = "+email);
	return super.recupera("email", email);
}




@RequestMapping(value="salvar", method = RequestMethod.POST, headers = "Accept=application/json")
@ResponseBody
public ResponseEntity<String> adicionar(@RequestBody Usuario usuario)throws Exception {
	
	//System.out.println("perfil 1 = "+usuario.getPerfil().getDescricao());

	try{
		

	//System.out.println("cpf = "+usuario.getCpf());
	//System.out.println("perfil = "+usuario.getPerfil().getDescricao());

//	return super.recupera("email", email);
	
	String erros2 = "";
	 
		List<String> erros = new ArrayList<String>();
		
		 Gson gson =  new GsonBuilder().create();
			 
	if (usuario.getNome() == null){

			erros2 += "1,";
			
	}
	if (usuario.getCpf() == null){

			erros2 += "3,";
	
	}	else{
		
		if (isCPF(usuario.getCpf().replace(".", "").replace("-", "")) == false){
			
			erros2 += "6,";
			
		}
	}
	
	if (usuario.getPerfil() == null){

		erros2 += "4,";

	}

	
	if (usuario.getEmail() == null){
		
		erros2 += "2,";
	
	}else{
		
	if (!validarEmail(usuario.getEmail())){
		erros2 += "7,";
		}
		
	}
	
	
	if (usuario.getSenha() == null || usuario.getRepeteSenha() == null ){
		erros2 += "5,";
	}
	else{
		
		if (!usuario.getSenha().equals(usuario.getRepeteSenha())){
			
			erros2 += "8,";
		
		}
		
	}
		
	
	//if (erros.isEmpty()){
	if (erros2 == ""){
		
		if (usuario.getId() == null){ // so verifica se existe cpf com mesmo
		 //if(super.recupera("cpf", usuario.getCpf()) != null || super.recupera("email", usuario.getEmail()) != null){
		if(super.recupera("cpf", usuario.getCpf()) != null){	 
			// erros2 += "Ops, j&aacute; existe um usuário cadastrado com esses dados ";
			 erros2 += "9,";
		 }
	}
	
	}
	
	//if (!erros.isEmpty()){
	if (erros2 != ""){
		 System.out.println("gson.toJson(erros) = "+gson.toJson(erros2));
	return new ResponseEntity<String>( gson.toJson(erros2),HttpStatus.BAD_REQUEST);
	}else
	{
		
		usuario.setAtivo(true);
		
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		usuario.setSenha(md5.encodePassword(usuario.getSenha(), null));
		
		super.salvarOuAtualizar(usuario);
		
		// nao enviar email no momento
		/*
		Usuario u = new Usuario();
		
		u  = super.recupera("email", usuario.getEmail());
				
				 System.out.println("cpf novo***********= "+u.getCpf());
				 
					// takes input from e-mail form
			        String recipientAddress = usuario.getEmail();
			        String subject = "Notificação de Cadastro";
			        String message = "Cadastro na Agenda do Paulo foi efetuado com sucesso! \n \n Seu login é: "+usuario.getEmail()+" \n \n "+ "e sua senha é: " + usuario.getSenha();//request.getParameter("message");
			         
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
			   //     mailSender.send(email2);
		*/
		
		 return new ResponseEntity<String>("Cadastrado com sucesso!",HttpStatus.OK);
	}
	
		 
	 
	 
	 
	}
	
	catch(Exception e){
		
		return new ResponseEntity<String>("Erro no Cadastrado = "+ e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	 
	 
	 
	 
	 
	 
	
	
}




	@RequestMapping(value="redefirnirSenha", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> redefinir(@RequestBody String email)throws Exception {
				
	try{
		

	System.out.println("redefinirSenha = "+email);
	
	if (email.equals("")){
		
		return new ResponseEntity<String>( "Informe o E-mail",HttpStatus.BAD_REQUEST);
	}
	else
	{

		if (super.recupera("email", email)==null){
			 
			return new ResponseEntity<String>( "E-mail inexistente",HttpStatus.BAD_REQUEST);
			
		
		}else
			{
		
			UUID uuid = UUID.randomUUID();  
			String myRandom = uuid.toString();	
			String senha = myRandom.substring(0,6);
			
			System.out.println(senha);
			
			// n atualizar na losango, n envia email
			
		/*	Usuario usu = super.recupera("email", email);
			
			usu.setSenha(senha);
			
			System.out.println("nova senha usu= "+ usu.getSenha());
			
			super.salvarOuAtualizar(usu);
			

					// takes input from e-mail form
			        String recipientAddress = "rgmichael@gmail.com";//email;
			        String subject = "Recuperação de Senha";
			        String message = "\n \n Sua nova senha é: "+senha+" \n \n ";//request.getParameter("message");
			         
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
		*/
		
			 return new ResponseEntity<String>("Nova senha enviada para o seu e-mail!",HttpStatus.OK);
		}
		
	}

	}
	
	catch(Exception e){
		
		return new ResponseEntity<String>("Erro ao Redefinir Senha",HttpStatus.BAD_REQUEST);	
	}
	
}
	
	@RequestMapping(value="excluir/{cod}", method=RequestMethod.POST)
	public  @ResponseBody ResponseEntity<String> excluir(@PathVariable("cod") String cod) throws Exception {

			Usuario usu = new Usuario();

			usu = super.loadObjeto(Long.parseLong(cod));

			super.deletar(usu);

			return new ResponseEntity<String>("Excluído com sucesso!",HttpStatus.OK);

	}

	@RequestMapping(value="ativarInativar/{cod}", method=RequestMethod.POST)
	public  @ResponseBody ResponseEntity<String> ativarInativar(@PathVariable("cod") String cod) throws Exception {

			Usuario usu = new Usuario();

			usu = super.loadObjeto(Long.parseLong(cod));
			
			if(usu.getAtivo()){
				usu.setAtivo(false);
			}
			else{
				usu.setAtivo(true);
			}

			super.salvarOuAtualizar(usu);

			return new ResponseEntity<String>("Operação efetuada com sucesso!",HttpStatus.OK);

	}
	
	

public static boolean isCPF(String CPF) {
	// considera-se erro CPF's formados por uma sequencia de numeros iguais
	    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
	        CPF.equals("22222222222") || CPF.equals("33333333333") ||
	        CPF.equals("44444444444") || CPF.equals("55555555555") ||
	        CPF.equals("66666666666") || CPF.equals("77777777777") ||
	        CPF.equals("88888888888") || CPF.equals("99999999999") ||
	       (CPF.length() != 11))
	       return(false);

	    char dig10, dig11;
	    int sm, i, r, num, peso;

	// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
	    try {
	// Calculo do 1o. Digito Verificador
	      sm = 0;
	      peso = 10;
	      for (i=0; i<9; i++) {              
	// converte o i-esimo caractere do CPF em um numero:
	// por exemplo, transforma o caractere '0' no inteiro 0         
	// (48 eh a posicao de '0' na tabela ASCII)         
	        num = (int)(CPF.charAt(i) - 48); 
	        sm = sm + (num * peso);
	        peso = peso - 1;
	      }

	      r = 11 - (sm % 11);
	      if ((r == 10) || (r == 11))
	         dig10 = '0';
	      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

	// Calculo do 2o. Digito Verificador
	      sm = 0;
	      peso = 11;
	      for(i=0; i<10; i++) {
	        num = (int)(CPF.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso - 1;
	      }

	      r = 11 - (sm % 11);
	      if ((r == 10) || (r == 11))
	         dig11 = '0';
	      else dig11 = (char)(r + 48);

	// Verifica se os digitos calculados conferem com os digitos informados.
	      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	         return(true);
	      else return(false);
	    } catch (InputMismatchException erro) {
	        return(false);
	    }
	  }

	  public static String imprimeCPF(String CPF) {
	    return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
	      CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
	  }
	  
	  public static boolean validarEmail(String email)
	    {
	        boolean isEmailIdValid = false;
	        
	        if (email != null && email.length() > 0) {	        	
	            String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; // "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	            Matcher matcher = pattern.matcher(email);
	            if (matcher.matches()) {
	                isEmailIdValid = true;
	            }
	        }
	        return isEmailIdValid;
	    }

}