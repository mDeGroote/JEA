/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.Kwetter;
import Models.KwetterUser;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * Interface implementation for persistence of Kwetters
 */
@Stateless @JPA
public class KwetterDaoImpl implements KwetterDao{

    @PersistenceContext(unitName = "KwetterPU")
    private EntityManager em;

    public KwetterDaoImpl() {
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
      
    @Override
    public Kwetter Create(Kwetter k) {
        k.setUser(em.merge(k.getUser()));
        k.setKwetterDate(LocalDate.now());
        em.persist(k);
        return k;
    }

    @Override
    public void Delete(int id) {
        em.remove(em.find(Kwetter.class, id));
    }

    @Override
    public Kwetter KwetterByID(int id) {
        return em.find(Kwetter.class, id);
    }

    @Override
    public List<Kwetter> Search(String s) {
        return em.createNativeQuery("SELECT * FROM Kwetter WHERE title LIKE '%" + s + "%' or content LIKE '%" + s + "%';", Kwetter.class).getResultList();
    }

    @Override
    public List<Kwetter> getKwettersFromUser(KwetterUser u) {
        Query query = em.createQuery("SELECT k FROM Kwetter k WHERE k.user = ?1");
        query.setParameter(1, u);
        return query.getResultList();
    }
    
}
