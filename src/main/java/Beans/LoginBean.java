/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Models.KwetterUser;
import Models.Roles;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
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
    
    @Inject
    private ExternalContext externalContext;

    private String username;
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

    public void Login() {
        Credential credential = new UsernamePasswordCredential(username, password);
        AuthenticationStatus status = securityContext.authenticate(
                getHttpRequestFromFacesContext(),
                getHttpResponseFromFacesContext(),
                withParams().credential(credential));
        if (status == status.SUCCESS) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/ModeratorMainScreen.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid credentials", null));
    }

    private HttpServletRequest getHttpRequestFromFacesContext() {
        return (HttpServletRequest) facesContext
                .getExternalContext()
                .getRequest();
    }

    private HttpServletResponse getHttpResponseFromFacesContext() {
        return (HttpServletResponse) facesContext
                .getExternalContext()
                .getResponse();
    }

}
