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
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todolist/users/{userId}/")
public class ListController {
    
    @Autowired
    private ListService listService;
    
    @GetMapping("lists")
    public java.util.List<List> getLists(@PathVariable int userId){
        return listService.getLists(userId);
    }
    
    @PostMapping("lists")
    public List addListToUser(@PathVariable int userId, @RequestBody List list){
        System.out.println("I am the list" + list);
        return listService.addListToUser(userId, list);
    }
    
    @GetMapping("lists/{listId}")
    public List getSingleList(@PathVariable int userId, @PathVariable int listId){
        return listService.getSingleList(userId, listId);
    }
    
    @Transactional
    @PutMapping("lists/{listId}")
    public List updateList(@PathVariable int userId, @PathVariable int listId, @RequestBody List list){
        return listService.updateList(userId, listId, list);
    }
    
    @Transactional
    @DeleteMapping("/lists/{listId}")
    public String deleteList(@PathVariable int userId, @PathVariable int listId){
       return listService.deleteList(userId, listId);
    }
}