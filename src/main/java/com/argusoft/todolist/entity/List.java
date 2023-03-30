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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author arpit
 */

@Entity
@Table(name="list")
public class List {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;
    
    @Column(name="task_count")
    private int taskCount;
    
//    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private java.util.List<Task> tasks;
        

    public List() {
    }

    public List(String title, String description, int taskCount, User user) {
        this.title = title;
        this.description = description;
        this.taskCount = taskCount;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public java.util.List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(java.util.List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "List{" + "id=" + id + ", title=" + title + ", description=" + description + ", taskCount=" + taskCount + ", user=" + user + ", tasks=" + tasks + '}';
    }
    
}
