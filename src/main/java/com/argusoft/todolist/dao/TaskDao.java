/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.repository.ListRepository;
import com.argusoft.todolist.utils.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author arpit
 */

public interface TaskDao {
    public TaskEntity createTask(int userId, int listId, Task task);    
    public TaskEntity getAllTasks(int userId, int listId);
    public TaskEntity getSingleTask(int userId, int listId, int taskId);
    public TaskEntity updateTask(int userId, int listId, int taskId, Task task);
    public TaskEntity deleteTask(int userId, int listId, int taskId);
}
