/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.utils;

import com.argusoft.todolist.entity.List;

/**
 *
 * @author arpit
 */


public class ListEntity {
    private List list;
    private boolean user;

    public ListEntity(List list, boolean isUser) {
        this.list = list;
        this.user = isUser;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean isUser) {
        this.user = isUser;
    }
    
}
