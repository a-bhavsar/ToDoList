/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.ListRepository;
import com.argusoft.todolist.repository.TaskRepository;
import com.argusoft.todolist.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */

@Service
public class TaskDaoImpl implements TaskDao {
    @Autowired
    private TaskRepository taskRepository; 
    
    @Autowired
    private ListRepository listRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private boolean checkUser(int userId){
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        if(theUser==null){
            return false;
        }
        return true;
    }
    
    private boolean checkList(int listId){
        Optional<List> list = listRepository.findById(listId);
        List theList = null;
        if(list.isPresent()){
            theList = list.get();
        }
        if(theList==null){
            return false;
        }
        return true;
    }
    
    @Override
    public Task createTask(int userId, int listId, Task task){
        if(!checkUser(userId)){
            return null;
        }
        Optional<List> list = listRepository.findById(listId);
        List theList = null;
        if(list.isPresent()){
            theList = list.get();
        }
        if(theList == null){
            return null;
        }
        task.setList(theList);
        taskRepository.save(task);
        return task;
    }
    
    @Override
    public java.util.List<Task> getAllTasks(int userId, int listId){
        if(!checkUser(userId)){
            return null;
        }
        java.util.List<Task> tasks = taskRepository.findAll();
        java.util.List<Task> myTasks = new ArrayList<>();
        for(Task task : tasks){
            if(task.getList().getId() == listId){
                myTasks.add(task);
            }
        }
        return myTasks;
    }

    @Override
    public Task getSingleTask(int userId, int listId, int taskId) {
        if(!checkUser(userId)){
            return null;
        }
        
        Optional<Task> task = taskRepository.findById(taskId);
        Task theTask = null;
        if(task.isPresent()){
            theTask = task.get();
        }
        return theTask;
    }
    
    @Override
    public Task updateTask(int userId, int listId, int taskId, Task task){
        if(!checkUser(userId)){
            return null;
        }
        if(!checkList(listId)){
            return null;
        }
        Optional<Task> t = taskRepository.findById(taskId);
        Task theTask = null;
        if(t.isPresent()){
            theTask = t.get();
        }
        if(theTask==null){
            return null;
        }
        theTask.setTitle(task.getTitle());
        theTask.setDescription(task.getDescription());
        theTask.setStatus(task.getStatus());
        theTask.setStartDate(task.getStartDate());
        theTask.setEndDate(task.getEndDate());
        taskRepository.save(theTask);
        return theTask;
    }
    
    @Override
    public String deleteTask(int userId, int listId, int taskId){
        if(!checkUser(userId)){
            return "User does not exists";
        }
        if(!checkList(listId)){
            return "List does not exists";
        }
        Optional<Task> task = taskRepository.findById(taskId);
        Task theTask = null;
        if(task.isPresent()){
            theTask = task.get();
        }
        if(theTask == null){
            return "Task does not exists";
        }
        taskRepository.delete(theTask);
        return "Task deleted with id - " + taskId;
    }
}
