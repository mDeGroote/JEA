/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.Kwetter;
import Models.KwetterUser;
import Models.account;
import java.util.List;

/**
 *
 * Interface for persistency of Kwetters
 */
public interface KwetterDao {
    public void Create(Kwetter k);
    public void Delete(Kwetter k);
    public Kwetter KwetterByID(int id);
    public List<Kwetter> AllKwettersFromUser(KwetterUser u);
    public List<Kwetter> Last10Kwetters(int id);
    public List<Kwetter> GetTimeline(KwetterUser u);
    public List<Kwetter> Search(String s);
}
