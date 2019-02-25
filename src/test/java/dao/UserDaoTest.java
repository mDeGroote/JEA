/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Dao.UserDao;
import Models.KwetterUser;
import Models.account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author mariac
 */
public class UserDaoTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
    private EntityManager em;
    private EntityTransaction et;
    private Dao.UserDaoImpl userDao;
    
    @Before
    public void setUp()
    {
        em = emf.createEntityManager();
        et = em.getTransaction();

        userDao = new Dao.UserDaoImpl();
        userDao.setEm(em);
    }
    /**
     * Test of Create method, of class UserDao.
     */
    @Test
    public void testCreate() {
        KwetterUser kwetterUser = new KwetterUser();
        et.begin();
        userDao.Create(kwetterUser);
        et.commit();
        assertEquals(kwetterUser, em.find(KwetterUser.class, kwetterUser.getId()));
    }
    
    @Test
    public void testUpdate() {
        KwetterUser kwetterUser = new KwetterUser();
        et.begin();
        userDao.Create(kwetterUser);
        et.commit();
        et.begin();
        kwetterUser.setName("newName");
        userDao.Update(kwetterUser);
        et.commit();
        assertEquals(kwetterUser.getName(), em.find(KwetterUser.class, kwetterUser.getId()).getName());
    }

    /**
     * Test of Delete method, of class UserDao.
     */
    @Test
    public void testDeleteUser() {
        KwetterUser u = new KwetterUser();
        et.begin();
        userDao.Create(u);
        et.commit();
        et.begin();
        userDao.DeleteUser(u);
        et.commit();
        assertNull(em.find(KwetterUser.class, u.getId()));
    }
    
    @Test
    public void testDeleteAccount() {
        account account = new account("deleteAccount", "deleteAccount");
        et.begin();
        userDao.registerUser(account);
        et.commit();
        et.begin();
        userDao.DeleteAccount(account);
        et.commit();
        assertNull(em.find(account.class, account.getId()));
    }

    /**
     * Test of UserByID method, of class UserDao.
     */
    @Test
    public void testUserByID() {
        KwetterUser kwetterUser = new KwetterUser();
        et.begin();
        em.persist(kwetterUser);
        et.commit();
        assertEquals(kwetterUser, userDao.UserByID(kwetterUser.getId()));
    }

    /**
     * Test of AllUsers method, of class UserDao.
     */
    @Test
    public void testAllUsers() {
        KwetterUser kwetterUser = new KwetterUser();
        et.begin();
        em.persist(kwetterUser);
        et.commit();
        assertNotNull(userDao.AllUsers());
    }
    
    @Test
    public void testRegister() {
        et.begin();
        userDao.registerUser(new account("RegisterTest", "RegisterTest"));
        et.commit();
        assertNotNull(userDao.Login("RegisterTest", "RegisterTest"));
    }
    
    @Test
    public void testLogin() {
        account account = new account("LoginTest", "LoginTest");
        et.begin();
        em.persist(account);
        et.commit();
        KwetterUser user = userDao.Login("LoginTest", "LoginTest");
        assertNotNull(user.getId());
    }
}
