/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.KwetterUser;
import Models.account;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;


@Stateless @Default
public class UserDaoColl implements UserDao{
    private List<KwetterUser> users = new ArrayList<>();

    @Override
    public void Create(KwetterUser u) {
        users.add(u);
    }

    @Override
    public void DeleteUser(KwetterUser u) {
        users.remove(u);
    }
    
    @Override
    public void DeleteAccount(account a) {
        users.remove(a.getKwetterUser());
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
    public void registerUser(account account) {
        users.add(account.getKwetterUser());
    }

    @Override
    public KwetterUser Login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(KwetterUser u) {
        for(KwetterUser user : users) {
            if(u.getId() == user.getId()) {
                user = u;
            }
        }
    } 
    
}
