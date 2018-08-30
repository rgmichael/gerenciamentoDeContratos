package curso.angular.seguranca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        
        System.out.println("entrou na validação com a role **- "+role );

        String targetUrl = "";
        if(role.contains("ADM")) {
            targetUrl = "/adm.html";
        } else if(role.contains("CLIENTE")) {
            targetUrl = "/inicial2.html";
        }
else if(role.contains("BARBEIRO")) {
	targetUrl = "negado/acesso-negado.html";
    }
        else {
        	
        	 targetUrl = "negado/acesso-negado.html";
        }
        	
        return targetUrl;
    }
}