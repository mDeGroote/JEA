/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import DTO.KwetterUserDTO;
import Models.KwetterUser;
import Models.account;
import java.util.List;

/**
 *
 * Interface for persistency of Users
 */
public interface UserDao {
    public KwetterUser Create(KwetterUser u);
    public KwetterUser Update(KwetterUser u);
    public void DeleteUser(KwetterUser u);
    public KwetterUser UserByID(int id);
    public List<KwetterUser> AllUsers();
    public KwetterUser Login(String username, String password);
}
