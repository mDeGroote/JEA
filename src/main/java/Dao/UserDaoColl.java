/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.KwetterUser;
import Models.account;
import com.sun.org.apache.regexp.internal.REUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;


@ApplicationScoped @Default
public class UserDaoColl implements UserDao{
    private List<KwetterUser> users = new ArrayList();

    @Override
    public KwetterUser Create(KwetterUser u) {
        users.add(u);
        return u;
    }

    @Override
    public void DeleteUser(KwetterUser u) {
        users.remove(u);
    }

    @Override
    public KwetterUser UserByID(int id) {
        for(KwetterUser user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<KwetterUser> AllUsers() {
        return users;
    }

    @Override
    public KwetterUser Login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KwetterUser Update(KwetterUser u) {
        for(KwetterUser user : users) {
            if(u.getId() == user.getId()) {
                user = u;
            }
        }
        return u;
    } 
    
}
