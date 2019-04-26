/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DTO.KwetterUserDTO;
import Dao.JPA;
import Dao.UserDao;
import Models.KwetterUser;
import Models.account;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * Service for the KwetterUser model
 */
@Stateless
public class KwetterUserService {
    @Inject @JPA
    private UserDao userDao;
    
    
    public KwetterUser Create(KwetterUser u) {
        return userDao.Create(u);
    }
    
    public KwetterUser Update(KwetterUserDTO user) {
        KwetterUser u = this.userByID(user.getId());
        u.setName(user.getName());
        u.setProfilePicture(user.getProfilePicture());
        u.setBio(user.getBio());
        u.setLocation(user.getLocation());
        u.setWebsite(user.getWebsite());
        return userDao.Update(u);
    }
    
    public void DeleteUser(KwetterUser u) {
        userDao.DeleteUser(u);
    }
    
    public List<KwetterUser> getAll() {
        return userDao.AllUsers();
    }
    
    public KwetterUser userByID(int id) {
        return userDao.UserByID(id);
    }
    
    public KwetterUser login(String username, String password) {
        return userDao.Login(username, password);
    }
    
    public KwetterUser follow(KwetterUser u, KwetterUser following) {
        u.follow(following);
        return userDao.Update(u);
    }
    
    public KwetterUser unfollow(KwetterUser u, KwetterUser following) {
        u.unfollow(following);
        return userDao.Update(u);
    }
 }
