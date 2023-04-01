/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.service;

import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.utils.TaskEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author arpit
 */
public interface TaskService {
    public TaskEntity createTask(int userId, int listId, Task task);
    public TaskEntity getAllTasks( int userId, int listId);
    public TaskEntity getSingleTask(int userId, int listId, int taskId);
    public TaskEntity updateTask(int userId, int listId, int taskId, Task task);
    public TaskEntity deleteTask(int userId, int listId, int taskId);
}
