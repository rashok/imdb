/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.imdb.beans;

/**
 *
 * @author 317306
 */
public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private int profile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }



}
