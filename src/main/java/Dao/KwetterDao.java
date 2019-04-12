/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import DTO.KwetterJsonDTO;
import Models.Kwetter;
import Models.KwetterUser;
import Models.account;
import java.util.List;

/**
 *
 * Interface for persistency of Kwetters
 */
public interface KwetterDao {
    public Kwetter Create(KwetterJsonDTO k);
    public Kwetter Create(Kwetter k);
    public void Delete(int id);
    public Kwetter KwetterByID(int id);
    public List<Kwetter> Search(String s);
    public List<Kwetter> getKwettersFromUser(KwetterUser u);
}
