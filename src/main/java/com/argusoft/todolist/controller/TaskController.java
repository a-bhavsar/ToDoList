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
import com.argusoft.todolist.utils.ResponseBodyObj;
import com.argusoft.todolist.utils.TaskEntity;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    
    @Autowired 
    private TaskService taskService;
    
   @PostMapping("tasks")
   public ResponseEntity<ResponseBodyObj<Task>> createTask(@PathVariable int userId, @PathVariable int listId, @RequestBody Task task){
       
       TaskEntity taskEntity = taskService.createTask(userId, listId, task);
       String message;
       HttpStatus statusCode;
       if(taskEntity.getTask()==null){
            if (taskEntity.isUser() && !taskEntity.isList()){
                message = "User not found";
                statusCode = HttpStatus.NOT_FOUND;
            }
            else{
                message = "List not found";
                statusCode = HttpStatus.NOT_FOUND;
            }
       }
       else{
           if(taskEntity.isDuplicateTask()){
               message = "Task Title already exists";
               statusCode = HttpStatus.NOT_ACCEPTABLE;
           }
           else{
               message = "Task created";
               statusCode = HttpStatus.OK;
           }
           
       }
       ResponseBodyObj obj = new ResponseBodyObj(taskEntity.getTask(), message, statusCode);
       return new ResponseEntity(obj, statusCode);
       
       
   }
   
   @GetMapping("tasks")
   public ResponseEntity<ResponseBodyObj<java.util.List<Task>>> getAllTasks(@PathVariable int userId, @PathVariable int listId){
       TaskEntity taskEntity =  taskService.getAllTasks(userId, listId);
       String message;
       HttpStatus statusCode;
       if(taskEntity.getTasks()==null){
           if(taskEntity.isUser() && !taskEntity.isList()){
               message = "User not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else{
               message = "List not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
       }
       else{
           message = "List of Tasks";
           statusCode = HttpStatus.OK;
       }
       ResponseBodyObj obj = new ResponseBodyObj(taskEntity.getTasks(), message, statusCode);
       return new ResponseEntity(obj, statusCode);
   }
   
   @GetMapping("tasks/{taskId}")
   public ResponseEntity<ResponseBodyObj<Task>> getSingleTask(@PathVariable int userId, @PathVariable int listId, @PathVariable int taskId){
       TaskEntity taskEntity = taskService.getSingleTask(userId, listId, taskId);
       String message;
       HttpStatus statusCode;
       if(taskEntity.getTask() == null){
           if(taskEntity.isUser() && !taskEntity.isList()){
               message = "User not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else if(!taskEntity.isUser() && taskEntity.isList()){
               message = "List not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else{
               message = "Task not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
       }
       else{
           message = "Task";
           statusCode = HttpStatus.OK;
       }
       ResponseBodyObj obj = new ResponseBodyObj(taskEntity.getTask(), message, statusCode);
       return new ResponseEntity(obj, statusCode);
   }
   
   @PutMapping("tasks/{taskId}")
   public ResponseEntity<ResponseBodyObj<Task>> updateTask(@PathVariable int userId, @PathVariable int listId, @PathVariable int taskId, @RequestBody Task task){
       TaskEntity taskEntity = taskService.updateTask(userId, listId, taskId, task);
       String message;
       HttpStatus statusCode;
       if(taskEntity.getTask() == null){
           if(taskEntity.isUser() && !taskEntity.isList()){
               message = "User not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else if(!taskEntity.isUser() && taskEntity.isList()){
               message = "List not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else{
               message = "Task not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
       }
       else{
           if(taskEntity.isDuplicateTask()){
               message = "Task Title already exists";
               statusCode = HttpStatus.NOT_ACCEPTABLE;
           }
           else{
               message = "Task Updated";
               statusCode = HttpStatus.OK;
           }
           
       }
       ResponseBodyObj obj = new ResponseBodyObj(taskEntity.getTask(), message, statusCode);
       return new ResponseEntity(obj, statusCode);
   }
   
   @DeleteMapping("tasks/{taskId}")
   public ResponseEntity<ResponseBodyObj<Task>> deleteTask(@PathVariable int userId, @PathVariable int listId, @PathVariable int taskId){
       TaskEntity taskEntity = taskService.deleteTask(userId, listId, taskId);
       String message;
       HttpStatus statusCode;
       if(taskEntity.getTask()==null){
           if(taskEntity.isUser() && !taskEntity.isList()){
               message = "User not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else if(!taskEntity.isUser() && taskEntity.isList()){
               message = "List not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
           else{
               message = "Task not found";
               statusCode = HttpStatus.NOT_FOUND;
           }
       }
       else{
           message = "Task deleted";
           statusCode = HttpStatus.OK;
       }
       ResponseBodyObj obj = new ResponseBodyObj(taskEntity.getTask(), message, statusCode);
       return new ResponseEntity(obj, statusCode);
   }
}
