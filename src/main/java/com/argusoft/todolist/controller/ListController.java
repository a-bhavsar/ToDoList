/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.controller;

import com.argusoft.todolist.dao.ListDao;
import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.argusoft.todolist.repository.UserRepository;
import com.argusoft.todolist.service.ListService;
import com.argusoft.todolist.utils.ListEntity;
import com.argusoft.todolist.utils.ResponseBodyObj;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todolist/users/{userId}/")
@CrossOrigin(origins = "http://localhost:4200")
public class ListController {
    
    @Autowired
    private ListService listService;
    
    @GetMapping("lists")
    public ResponseEntity<ResponseBodyObj<java.util.List<List>>> getLists(@PathVariable int userId){
        java.util.List<List> lists = listService.getLists(userId);
        String message;
        HttpStatus statusCode = HttpStatus.OK;
        if(lists.size() == 0){
            message = "No Lists Found";
        }
        else{
            message = "List of Lists";
        }
        ResponseBodyObj obj = new ResponseBodyObj(lists, message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
    
    @PostMapping("lists")
    public ResponseEntity<ResponseBodyObj<ListEntity>> addListToUser(@PathVariable int userId, @RequestBody List list){
        ListEntity l = listService.addListToUser(userId, list);
        String message;
        HttpStatus statusCode;
        if(l.getList() == null){
            message = "User not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else{
            if(l.isDuplicateList()){
                message = "List Title already exists";
                statusCode = HttpStatus.NOT_ACCEPTABLE;
            }
            else{
                message = "List Added Successfully";
                statusCode = HttpStatus.OK;
            }
        }
        ResponseBodyObj obj = new ResponseBodyObj(l, message, statusCode);
        return new ResponseEntity(obj, statusCode);
        
    }
    
    @GetMapping("lists/{listId}")
    public ResponseEntity<ResponseBodyObj<List>> getSingleList(@PathVariable int userId, @PathVariable int listId){
        ListEntity l = listService.getSingleList(userId, listId);
        String message;
        HttpStatus statusCode;
        if(l.getList()==null && l.isUser()){
            message = "User not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else if(l.getList()==null && !l.isUser()){
            message =  "List not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else{
            message = "List found";
            statusCode = HttpStatus.OK;  
        }
        ResponseBodyObj obj = new ResponseBodyObj(l.getList(), message, statusCode);
        return new ResponseEntity<>(obj, statusCode);
    }
    
    @Transactional
    @PutMapping("lists/{listId}")
    public ResponseEntity<ResponseBodyObj<List>> updateList(@PathVariable int userId, @PathVariable int listId, @RequestBody List list){
        ListEntity listEntity = listService.updateList(userId, listId, list);
        String message;
        HttpStatus statusCode;
        if(listEntity.getList()==null && listEntity.isUser()){
            message = "User not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else if(listEntity.getList()==null && !listEntity.isUser()){
            message = "List not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else if(listEntity.isDuplicateList()){
            message = "List Title already exists";
            statusCode = HttpStatus.NOT_ACCEPTABLE;
        }
        else{
            message = "List updated";
            statusCode = HttpStatus.OK;
        }
        ResponseBodyObj obj = new ResponseBodyObj(listEntity.getList(), message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
    
    @Transactional
    @DeleteMapping("/lists/{listId}")
    public ResponseEntity<ResponseBodyObj<List>> deleteList(@PathVariable int userId, @PathVariable int listId){
       ListEntity listEntity = listService.deleteList(userId, listId);
       String message;
       HttpStatus statusCode;
       if(listEntity.getList()==null && listEntity.isUser()){
           message = "User not found";
           statusCode = HttpStatus.NOT_FOUND;
       }
       else if(listEntity.getList()==null && !listEntity.isUser()){
           message = "List not found";
           statusCode = HttpStatus.NOT_FOUND;
       }
       else{
           message = "List deleted";
           statusCode = HttpStatus.OK;
       }
        ResponseBodyObj obj = new ResponseBodyObj(listEntity.getList(), message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
}