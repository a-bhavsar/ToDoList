/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;

/**
 *
 * @author arpit
 */

@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Column(name="mobile_no")
    private String mobileNo;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private java.util.List<List> lists;
    
    @JsonIgnore
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy= "user")
    private java.util.List<Task> tasks;

    public User() {
    }

    public User(String username, String password, String mobileNo) {
        this.username = username;
        this.password = password;
        this.mobileNo = mobileNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public java.util.List<List> getLists() {
        if(lists == null){
            return new ArrayList<List>();
        }
        return lists;
    }

    public void setLists(java.util.List<List> lists) {
        this.lists = lists;
    }

    public java.util.List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(java.util.List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", mobileNo=" + mobileNo + ", lists=" + lists + ", tasks=" + tasks + '}';
    }
    
}
