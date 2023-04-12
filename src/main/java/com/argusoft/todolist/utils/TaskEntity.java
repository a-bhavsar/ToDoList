/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.utils;

import com.argusoft.todolist.entity.Task;
import java.util.List;

/**
 *
 * @author arpit
 */
public class TaskEntity {
    private Task task;
    private boolean user;
    private boolean list;
    private java.util.List<Task> tasks;
    private boolean duplicateTask;

    public TaskEntity() {
    }

    public TaskEntity(Task task, boolean user, boolean list) {
        this.task = task;
        this.user = user;
        this.list = list;
    }

    public TaskEntity(boolean user, boolean list, List<Task> tasks) {
        this.tasks = tasks;
        this.user = user;
        this.list = list;    
    }
    
    public TaskEntity(Task task, boolean user, boolean list, boolean duplicateTask){
        this.task = task;
        this.user = user;
        this.list = list;
        this.duplicateTask = duplicateTask;
        
    }

    public boolean isDuplicateTask() {
        return duplicateTask;
    }

    public void setDuplicateTask(boolean duplicateTask) {
        this.duplicateTask = duplicateTask;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TaskEntity{" + "task=" + task + ", user=" + user + ", list=" + list + ", tasks=" + tasks + '}';
    }
    
    
    
}
