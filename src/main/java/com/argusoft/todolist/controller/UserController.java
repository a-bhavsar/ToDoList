/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.controller;

import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import com.argusoft.todolist.service.UserService;
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
@RequestMapping("/todolist/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public java.util.List<User> getAllUsers(){
        System.out.println("Inside get all users");
        return userService.getAllUser();
    }
    
    @GetMapping("{userId}")
    public User getUser(@PathVariable int userId){
        return userService.getUser(userId);
    }
   
    @PostMapping("")
    public User createUser(@RequestBody User user){
        System.out.println("Here I am Virat"+user);
        return userService.createUser(user);
    }
    
    @PutMapping("{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user){
        return userService.updateUser(userId, user);
    }
    
    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }
}
