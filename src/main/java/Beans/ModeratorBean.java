/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Models.Kwetter;
import Models.KwetterUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import services.KwetterService;
import services.KwetterUserService;


@Named("ModeratorBean")
@SessionScoped
public class ModeratorBean implements Serializable{
    @Inject
    private KwetterService kwetterService;
    
    @Inject
    private KwetterUserService kwetterUserService;
    
    @Inject
    private SecurityContext securityContext;
    
    private int moderatorID;
    private KwetterUser moderator;
    private List<Kwetter> selectedKwetters = new ArrayList();

    public KwetterUser getModerator() {
        return moderator;
    }

    public void setModerator(KwetterUser moderator) {
        this.moderator = moderator;
    }

    public int getModeratorID() {
        return moderatorID;
    }

    public void setModeratorID(int moderatorID) {
        this.moderatorID = moderatorID;
    }
    
    public void LoadModerator() {
        this.moderator = kwetterUserService.userByID(moderatorID);
    }
    
    public List<KwetterUser> getUsers() {
        return kwetterUserService.getAll();
    }  
    
    public void getKwettersFromUser(KwetterUser u) {
        this.selectedKwetters = kwetterService.getKwettersFromUser(u);
    }

    public List<Kwetter> getSelectedKwetters() {
        return selectedKwetters;
    }

    public void setSelectedKwetters(List<Kwetter> selectedKwetters) {
        this.selectedKwetters = selectedKwetters;
    }
    
    public void deleteKwetter(Kwetter k) {
        this.kwetterService.Delete(k.getId());
        this.getKwettersFromUser(k.getUser());
    }
    
    public String Logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/Login.xhtml";
    }
}
