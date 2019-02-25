/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * Model for the messages of the application
 */
@Entity
public class Kwetter implements Serializable, Comparable<Kwetter>{
    @Id @GeneratedValue
    private int id;
    private String title;
    private String content;
    @ManyToOne(targetEntity = KwetterUser.class)
    private KwetterUser user;
    private String kwetterDate;

    public Kwetter() {
        this.kwetterDate = LocalDate.now().toString();
    }

    public Kwetter(String title, String content, KwetterUser user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.kwetterDate = LocalDate.now().toString();
    } 

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public KwetterUser getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(KwetterUser user) {
        this.user = user;
    }

    public void setKwetterDate(String kwetterDate) {
        this.kwetterDate = kwetterDate;
    } 

    public String getKwetterDate() {
        return kwetterDate;
    }

    @Override
    public int compareTo(Kwetter o) {
        return this.kwetterDate.compareTo(o.getKwetterDate());
    }
}
