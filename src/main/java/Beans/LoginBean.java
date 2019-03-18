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
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import services.KwetterUserService;

@Named("LoginBean")
@RequestScoped
public class LoginBean implements Serializable{
    @Inject
    KwetterUserService service;
    
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
    
    public String Login() {
        KwetterUser u = service.login(username, password);
        if(u != null && u.getRole() == Roles.Moderator) {
            this.kwetteruser = u;
            return "ModeratorMainScreen.xhtml?";
        }
        return "Login.xhtml";
    }
}
