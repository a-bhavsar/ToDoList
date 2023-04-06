package com.argusoft.todolist.utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author arpit
 */
public class CheckUser {
    private boolean validUsername;
    private boolean validPassword;

    public CheckUser() {
    }

    public CheckUser(boolean validUsername, boolean validPassword) {
        this.validUsername = validUsername;
        this.validPassword = validPassword;
    }
    
    public boolean isValidUsername() {
        return validUsername;
    }

    public void setValidUsername(boolean validUsername) {
        this.validUsername = validUsername;
    }

    public boolean isValidPassword() {
        return validPassword;
    }

    public void setValidPassword(boolean validPassword) {
        this.validPassword = validPassword;
    }

    @Override
    public String toString() {
        return "CheckUser{" + "validUsername=" + validUsername + ", validPassword=" + validPassword + '}';
    }
    
    
}
