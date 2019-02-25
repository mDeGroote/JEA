package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Dao.KwetterDaoImpl;
import Models.Kwetter;
import Models.KwetterUser;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class KwetterDaoTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
    private EntityManager em;
    private EntityTransaction et;
    private KwetterDaoImpl kwetterDao;
    
    @Before
    public void setUp()
    {
        em = emf.createEntityManager();
        et = em.getTransaction();

        kwetterDao = new KwetterDaoImpl();
        kwetterDao.setEm(em);
    }
    /**
     * Test of Create method, of class KwetterDaoImpl.
     */
    @Test
    public void testCreate() throws Exception {
        KwetterUser kwetterUser = new KwetterUser();
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        et.begin();
        em.persist(kwetterUser);
        kwetterDao.Create(kwetter);
        et.commit();    
        assertEquals(em.find(Kwetter.class, kwetter.getId()), kwetter);
    }

    /**
     * Test of Delete method, of class KwetterDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        Kwetter kwetter = new Kwetter();
        et.begin();
        em.persist(kwetter);
        et.commit();
        em.remove(kwetter);
        assertNull(em.find(Kwetter.class, kwetter.getId()));
    }

    /**
     * Test of KwetterByID method, of class KwetterDaoImpl.
     */
    @Test
    public void testKwetterByID() throws Exception {
        Kwetter kwetter = new Kwetter();
        et.begin();
        em.persist(kwetter);
        et.commit();
        assertEquals(kwetter, kwetterDao.KwetterByID(kwetter.getId()));
    }

    /**
     * Test of AllKwettersFromUser method, of class KwetterDaoImpl.
     */
    @Test
    public void testAllKwettersFromUser() throws Exception {
        KwetterUser kwetterUser = new KwetterUser();
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        et.begin();
        em.persist(kwetterUser);
        em.persist(kwetter);
        et.commit();
        List<Kwetter> kwetters = kwetterDao.AllKwettersFromUser(kwetterUser);
        assertEquals(kwetter, kwetters.get(0));
    }

    /**
     * Test of Last10Kwetters method, of class KwetterDaoImpl.
     */
    @Test
    public void testLast10Kwetters() throws Exception {
        KwetterUser kwetterUser = new KwetterUser();
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        et.begin();
        em.persist(kwetterUser);
        em.persist(kwetter);
        et.commit();
        List<Kwetter> kwetters = kwetterDao.Last10Kwetters(kwetterUser.getId());
        assertEquals(kwetter, kwetters.get(0));
    }

    /**
     * Test of GetTimeline method, of class KwetterDaoImpl.
     */
    @Test
    public void testGetTimeline() throws Exception {
        KwetterUser kwetterUser = new KwetterUser();
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        et.begin();
        em.persist(kwetterUser);
        em.persist(kwetter);
        et.commit();
        List<Kwetter> kwetters = kwetterDao.GetTimeline(kwetterUser);
        assertEquals(kwetter, kwetters.get(0));
    }

    /**
     * Test of Search method, of class KwetterDaoImpl.
     */
    @Test
    public void testSearch() throws Exception {
        KwetterUser kwetterUser = new KwetterUser();
        Kwetter kwetter = new Kwetter("title", "stukje tekst om te kijken of de search functie wel het woord goed kan vinden", kwetterUser);
        et.begin();
        em.persist(kwetterUser);
        em.persist(kwetter);
        et.commit();
        List<Kwetter> kwetters = kwetterDao.Search("search");
        assertEquals(kwetter, kwetters.get(0));
    }
    
}
