/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * Model for the users of the application
 */
@Entity
public class KwetterUser implements Serializable {
    @Id @GeneratedValue
    private int id;
    private String name;
    private byte[] profilePicture;
    @ManyToMany
    @ElementCollection
    private List<KwetterUser> followers;
    @ManyToMany
    @ElementCollection
    private List<KwetterUser> following;
    private String bio;
    private String website;
    private String locatie;

    public KwetterUser() {
    }

    public KwetterUser(String name, byte[] profilePicture, List<KwetterUser> followers, List<KwetterUser> following, String bio, String website, String locatie) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.website = website;
        this.locatie = locatie;
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

    public String getLocatie() {
        return locatie;
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

    public void setLocatie(String locatie) {
        this.locatie = locatie;
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
    
    
    
}
