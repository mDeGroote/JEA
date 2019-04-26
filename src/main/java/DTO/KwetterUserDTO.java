/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Models.Roles;

public class KwetterUserDTO {
    private int id;
    private String name;
    private byte[] profilePicture;
    private String bio;
    private String website;
    private String location;

    public KwetterUserDTO() {
    }

    public KwetterUserDTO(int id, String name, byte[] profilePicture, String bio, String website, String location, Roles userRole) {
        this.id = id;
        this.name = name;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.website = website;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
