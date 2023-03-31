/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author arpit
 */

public interface TaskDao {
    public Task createTask(int userId, int listId, Task task);    
    public java.util.List<Task> getAllTasks(int userId, int listId);
    public Task getSingleTask(int userId, int listId, int taskId);
    public Task updateTask(int userId, int listId, int taskId, Task task);
    public String deleteTask(int userId, int listId, int taskId);
}
