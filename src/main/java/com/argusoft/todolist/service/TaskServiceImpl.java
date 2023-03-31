/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.service;

import com.argusoft.todolist.dao.TaskDao;
import com.argusoft.todolist.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */

@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskDao taskDao;

    @Override
    public Task createTask(int userId, int listId, Task task) {
        return taskDao.createTask(userId, listId, task);
    }

    @Override
    public java.util.List<Task> getAllTasks(int userId, int listId) {
        return taskDao.getAllTasks(userId, listId);
    }

    @Override
    public Task getSingleTask(int userId, int listId, int taskId) {
        return taskDao.getSingleTask(userId, listId, taskId);
    }
    
    @Override
    public Task updateTask(int userId, int listId, int taskId, Task task){
        return taskDao.updateTask(userId, listId, taskId, task);
    }

    @Override
    public String deleteTask(int userId, int listId, int taskId) {
        return taskDao.deleteTask(userId, listId, taskId);
    }
    
    
    
    
}
