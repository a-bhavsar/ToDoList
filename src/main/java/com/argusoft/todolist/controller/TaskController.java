/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.controller;

import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import com.argusoft.todolist.service.TaskService;
import com.argusoft.todolist.service.UserService;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arpit
 */

@RestController
@RequestMapping("/todolist/users/{userId}/lists/{listId}/")
public class TaskController {
    
    @Autowired 
    private TaskService taskService;
    
   @PostMapping("tasks")
   public Task createTask(@PathVariable int userId, @PathVariable int listId, @RequestBody Task task){
       return taskService.createTask(userId, listId, task);
   }
   
   @GetMapping("tasks")
   public java.util.List<Task> getAllTasks(@PathVariable int userId, @PathVariable int listId){
       return taskService.getAllTasks(userId, listId);
   }
   
   @GetMapping("tasks/{taskId}")
   public Task getSingleTask(@PathVariable int userId, @PathVariable int listId, @PathVariable int taskId){
       return taskService.getSingleTask(userId, listId, taskId);
   }
   
   @PutMapping("tasks/{taskId}")
   public Task updateTask(@PathVariable int userId, @PathVariable int listId, @PathVariable int taskId, @RequestBody Task task){
       return taskService.updateTask(userId, listId, taskId, task);
   }
   
   @DeleteMapping("tasks/{taskId}")
   public String deleteTask(@PathVariable int userId, @PathVariable int listId, @PathVariable int taskId){
       return taskService.deleteTask(userId, listId, taskId); 
   }
}
