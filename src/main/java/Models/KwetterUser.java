/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * Model for the users of the application
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class KwetterUser implements Serializable {
    @Id @GeneratedValue
    private int id;
    private String name;
    private byte[] profilePicture;
    @ManyToMany(mappedBy = "following")
    private List<KwetterUser> followers = new ArrayList();
    @ManyToMany
    private List<KwetterUser> following = new ArrayList();
    private String bio;
    private String website;
    private String location;
    @OneToMany(mappedBy = "user")
    private List<Kwetter> kwetters = new ArrayList();
    @Enumerated(EnumType.STRING)
    private Roles userRole;

    public KwetterUser() {
        
    }

    public KwetterUser(String name, byte[] profilePicture, List<KwetterUser> followers, List<KwetterUser> following, String bio, String website, String location, List<Kwetter> kwetters, Roles role) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.website = website;
        this.location = location;
        this.kwetters = kwetters;
        this.userRole = role;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public List<KwetterUser> getFollowers() {
        return followers;
    }

    public List<KwetterUser> getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }

    public Roles getRole() {
        return userRole;
    }    
    
    public void setId(int id) {
        this.id = id;
    } 
    
    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRole(Roles role) {
        this.userRole = role;
    }
    
    
    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setFollowers(List<KwetterUser> followers) {
        this.followers = followers;
    }

    public void setFollowing(List<KwetterUser> following) {
        this.following = following;
    }

    public List<Kwetter> getKwetters() {
        return kwetters;
    }

    public void setKwetters(List<Kwetter> kwetters) {
        this.kwetters = kwetters;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }
    
    public void addKwetter(Kwetter k) {
        this.kwetters.add(k);
    }
    
    public void removeKwetter(Kwetter k) {
        this.kwetters.remove(k);
    }
    
    public void follow(KwetterUser u) {
        this.following.add(u);
        u.addFollower(this);
    }
    
    public void unfollow(KwetterUser u) {
        this.following.remove(u);
        u.removeFollower(this);
    }
    
    public void addFollower(KwetterUser u) {
        this.followers.add(u);
    }
    
    public void removeFollower(KwetterUser u) {
        this.followers.add(u);
    }
    
    public List<Kwetter> timeline() {
        List<Kwetter> timeline = this.kwetters;
        timeline.sort(null);
        for(KwetterUser u : this.following) {
            timeline.addAll(u.getKwetters());
            timeline.sort(null);
        }
        return timeline;
    }
}
