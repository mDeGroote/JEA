/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.KwetterUser;
import Models.account;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * Interface implementation for persistence of users
 */
@Stateless @JPA
public class UserDaoImpl implements UserDao{
    @PersistenceContext(unitName = "KwetterPU")
    private EntityManager em;

    public UserDaoImpl() {
    }
    
    @Override
    public KwetterUser Create(KwetterUser u) {
        em.persist(u);
        return u;
    }
    
    @Override
    public KwetterUser Update(KwetterUser u) {
        return em.merge(u);
    }
    
    @Override
    public void DeleteUser(KwetterUser u) {
        em.remove(em.find(KwetterUser.class, u.getId()));
    }
    
    @Override
    public void DeleteAccount(account acc) {
        em.remove(em.find(account.class, acc.getId()));
    }

    @Override
    public KwetterUser UserByID(int id) {
        return em.find(KwetterUser.class, id);
    }

    @Override
    public List<KwetterUser> AllUsers() {
        return em.createQuery("SELECT u FROM KwetterUser u").getResultList();
    }
    
    @Override
    public void registerUser(account account) {
        this.em.persist(account);
    }
    
    @Override
    public KwetterUser Login(String username, String password) {
        Query query = this.em.createQuery("SELECT a.user FROM account a WHERE a.username = ?1 AND a.password = ?2");
        query.setParameter(1, username);
        query.setParameter(2, password);
        try {
            return (KwetterUser)query.getSingleResult();
        }
        catch(NoResultException ex) {
            return null;
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
