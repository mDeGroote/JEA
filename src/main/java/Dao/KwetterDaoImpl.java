/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.Kwetter;
import Models.KwetterUser;
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
    public void Create(Kwetter k) {
        em.persist(k);
    }

    @Override
    public void Delete(Kwetter k) {
        em.remove(k);
    }

    @Override
    public Kwetter KwetterByID(int id) {
        return em.find(Kwetter.class, id);
    }

    @Override
    public List<Kwetter> AllKwettersFromUser(KwetterUser u) {
        Query query = em.createQuery("SELECT k FROM Kwetter k WHERE k.user = ?1");
        query.setParameter(1, u);
        return query.getResultList();
    }

    @Override
    public List<Kwetter> Last10Kwetters(int id) {
        Query query = em.createNativeQuery("SELECT * FROM Kwetter WHERE user_id = ?1 ORDER BY id DESC LIMIT 10", Kwetter.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public List<Kwetter> GetTimeline(KwetterUser u) {
        List<Kwetter> kwetters = AllKwettersFromUser(u);
        for(KwetterUser ku : u.getFollowing()) {
            kwetters.addAll(AllKwettersFromUser(ku));
        }
        kwetters.sort(null);
        return kwetters;
    }

    @Override
    public List<Kwetter> Search(String s) {
        return em.createNativeQuery("SELECT * FROM Kwetter WHERE title LIKE '%" + s + "%' or content LIKE '%" + s + "%';", Kwetter.class).getResultList();
    }
    
}
