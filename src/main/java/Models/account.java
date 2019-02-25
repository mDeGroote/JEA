/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * account class to store username and password of KwetterUser
 */
@Entity
public class account {
    @Id @GeneratedValue
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private KwetterUser user;

    public account() {
        this.user = new KwetterUser();
    }

    
    public account(String password, String username) {
        this.user = new KwetterUser();
        this.password = password;
        this.username = username;
    }

    public KwetterUser getKwetterUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public KwetterUser getUser() {
        return user;
    }

    public void setUser(KwetterUser user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }
    
    
}
