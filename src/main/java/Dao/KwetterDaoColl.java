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
import javax.enterprise.inject.Default;
import sun.net.www.content.audio.x_aiff;

/**
 *
 * Collection implementation of KwetterDao interface
 */
@Stateless @Default
public class KwetterDaoColl implements KwetterDao{
    private List<Kwetter> kwetters = new ArrayList<>();

    @Override
    public void Create(Kwetter k) {
        kwetters.add(k);
    }

    @Override
    public void Delete(Kwetter k) {
        kwetters.remove(k);
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
    public List<Kwetter> AllKwettersFromUser(KwetterUser u) {
        List<Kwetter> kwettersFromUser = new ArrayList();
        for(Kwetter kwetter: kwetters) {
            if(kwetter.getUser().getId() == u.getId()) {
                kwettersFromUser.add(kwetter);
            }
        }
        return kwettersFromUser;
    }

    @Override
    public List<Kwetter> Last10Kwetters(int id) {
        List<Kwetter> kwettersFromUser = new ArrayList();
        for(Kwetter kwetter: kwetters) {
            if(kwetter.getUser().getId() == id) {
                kwettersFromUser.add(kwetter);
                if(kwettersFromUser.size() == 10) {
                    break;
                }
            }
        }
        return kwettersFromUser;
    }

    @Override
    public List<Kwetter> GetTimeline(KwetterUser u) {
        List<Kwetter> timeline = new ArrayList();
        kwetters.stream().filter(kwetter -> kwetter.getUser().getId() == u.getId()).iterator().forEachRemaining((t) -> {
            timeline.add(t);
        });
        for(KwetterUser user: u.getFollowing()) {
            kwetters.stream().filter(kwetter -> kwetter.getUser().getId() == user.getId()).iterator().forEachRemaining((t) -> {
            timeline.add(t);
            }); 
        }
        return timeline;
    }

    @Override
    public List<Kwetter> Search(String s) {
        List<Kwetter> searchResult = new ArrayList();
        kwetters.stream().filter(kwetter -> kwetter.getTitle().contains(s) || kwetter.getContent().contains(s)).iterator().forEachRemaining((t) -> {
            searchResult.add(t);
        });
        return searchResult;
    }
    
}
