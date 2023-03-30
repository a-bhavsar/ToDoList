/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.controller;

import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arpit
 */

@RestController
@RequestMapping("/todolist/users/{userId}/lists/{listId}")
public class TaskController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @GetMapping("/tasks")
    public java.util.List<Task> getAllTasks(@PathVariable int userId, @PathVariable int listId){
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        java.util.List<List> lists = theUser.getLists();
        for(List l : lists){
            if(l.getId() == listId){
                java.util.List<Task> tasks = l.getTasks();
                return tasks;
            }
        }
        return null;
    }
    
    @PostMapping("/tasks")
    public Task createTask(@PathVariable int userId, @PathVariable int listId, @RequestBody Task task){
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        java.util.List<List> lists = theUser.getLists();
        for(List l : lists){
            if(l.getId() == listId){
                java.util.List<Task> tasks = l.getTasks();
                tasks.add(task);
                theUser.setTasks(tasks);
                userRepository.save(theUser);
                return task;
            }
        }
        return null;
    }
}
