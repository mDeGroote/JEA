/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Kwetter implements Serializable, Comparable<Kwetter>{
    @Id @GeneratedValue
    private int id;
    private String content;
    @ManyToOne
    private KwetterUser user;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate kwetterDate;

    public Kwetter() {
        
    }

    public Kwetter(String content, KwetterUser user) {
        this.content = content;
        this.user = user;
        this.kwetterDate = LocalDate.now();
    } 

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public KwetterUser getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(KwetterUser user) {
        this.user = user;
    }

    public void setKwetterDate(LocalDate kwetterDate) {
        this.kwetterDate = kwetterDate;
    } 

    public LocalDate getKwetterDate() {
        return kwetterDate;
    }

    @Override
    public int compareTo(Kwetter o) {
        return this.kwetterDate.compareTo(o.getKwetterDate());
    }
}
