/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.service;

import com.argusoft.todolist.entity.Task;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author arpit
 */
public interface TaskService {
    public Task createTask(int userId, int listId, Task task);
    public java.util.List<Task> getAllTasks( int userId, int listId);
    public Task getSingleTask(int userId, int listId, int taskId);
    public Task updateTask(int userId, int listId, int taskId, Task task);
    public String deleteTask(int userId, int listId, int taskId);
}
