/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.Kwetter;
import Models.KwetterUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import sun.net.www.content.audio.x_aiff;

/**
 *
 * Collection implementation of KwetterDao interface
 */
@ApplicationScoped @Default
public class KwetterDaoColl implements KwetterDao{
    private List<Kwetter> kwetters = new ArrayList<>();

    @Override
    public Kwetter Create(Kwetter k) {
        kwetters.add(k);
        return k;
    }

    @Override
    public void Delete(int id) {
        for(Kwetter k : kwetters) {
            if(k.getId() == id) {
                kwetters.remove(k);
            }
        }
    }

    @Override
    public Kwetter KwetterByID(int id) {
        for(Kwetter kwetter : kwetters) {
            if(kwetter.getId() == id) {
                return kwetter;
            }
        }
        return null;
    }

    @Override
    public List<Kwetter> Search(String s) {
        List<Kwetter> searchResult = new ArrayList();
        kwetters.stream().filter(kwetter -> kwetter.getTitle().contains(s) || kwetter.getContent().contains(s)).iterator().forEachRemaining((t) -> {
            searchResult.add(t);
        });
        return searchResult;
    }

    @Override
    public List<Kwetter> getKwettersFromUser(KwetterUser u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
