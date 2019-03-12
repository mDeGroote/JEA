/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Dao.JPA;
import Dao.KwetterDao;
import Models.Kwetter;
import Models.KwetterUser;
import Models.account;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * Service for the Kwetter model
 */
@Stateless
public class KwetterService{
    @Inject @JPA
    private KwetterDao kwetterDao;
    
    public Kwetter Create(Kwetter k) {
        return kwetterDao.Create(k);
    }
    
    public void Delete(Kwetter k) {
        kwetterDao.Delete(k);
    }
    
    public Kwetter kwetterByID(int id) {
        return kwetterDao.KwetterByID(id);
    }
    
    public List<Kwetter> search(String s) {
        return kwetterDao.Search(s);
    }
}
