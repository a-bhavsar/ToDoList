/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.service;

import com.argusoft.todolist.dao.TaskDao;
import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.utils.TaskEntity;
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
    public TaskEntity createTask(int userId, int listId, Task task) {
        return taskDao.createTask(userId, listId, task);
    }

    @Override
    public TaskEntity getAllTasks(int userId, int listId) {
        return taskDao.getAllTasks(userId, listId);
    }

    @Override
    public TaskEntity getSingleTask(int userId, int listId, int taskId) {
        return taskDao.getSingleTask(userId, listId, taskId);
    }
    
    @Override
    public TaskEntity updateTask(int userId, int listId, int taskId, Task task){
        return taskDao.updateTask(userId, listId, taskId, task);
    }

    @Override
    public TaskEntity deleteTask(int userId, int listId, int taskId) {
        return taskDao.deleteTask(userId, listId, taskId);
    }
    
    
    
    
}
