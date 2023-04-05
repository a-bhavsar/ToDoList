/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.utils;

import com.argusoft.todolist.entity.User;

/**
 *
 * @author arpit
 */
public class UserEntity {
    private User user;
    private boolean validUser;
    private boolean notUser;
    private boolean validMobile;

    public UserEntity() {
    }

    public UserEntity(User user, boolean validUser, boolean notUser) {
        this.user = user;
        this.validUser = validUser;
        this.notUser = notUser;
    }
    
    public UserEntity(User user, boolean validUser, boolean notUser, boolean validMobile) {
        this.user = user;
        this.validUser = validUser;
        this.notUser = notUser;
        this.validMobile = validMobile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValidUser() {
        return validUser;
    }

    public void setValidUser(boolean validUser) {
        this.validUser = validUser;
    }

    public boolean isNotUser() {
        return notUser;
    }

    public void setNotUser(boolean notUser) {
        this.notUser = notUser;
    }

    public boolean isValidMobile() {
        return validMobile;
    }

    public void setValidMobile(boolean validMobile) {
        this.validMobile = validMobile;
    }
}
