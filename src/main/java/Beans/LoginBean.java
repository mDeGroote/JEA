/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Models.KwetterUser;
import Models.Roles;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.KwetterUserService;

@Named("LoginBean")
@RequestScoped
@FacesConfig
public class LoginBean implements Serializable{
    
    @Inject
    KwetterUserService service;
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private FacesContext facesContext;
    
    private String username = "username";
    private String password;     
    private KwetterUser kwetteruser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String Login() {
        KwetterUser u = service.login(username, password);
        if(u != null && u.getRole() == Roles.Moderator) {
            return "ModeratorMainScreen.xhtml";
        }
        return "Login.xhtml";
    }

    
}
