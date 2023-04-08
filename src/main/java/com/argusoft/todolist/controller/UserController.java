/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.controller;

import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import com.argusoft.todolist.service.UserService;
import com.argusoft.todolist.utils.ResponseBodyObj;
import com.argusoft.todolist.utils.UserEntity;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/todolist/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public ResponseEntity<ResponseBodyObj<java.util.List<User>>> getAllUsers(){
        java.util.List<User> users = userService.getAllUser();
        String message;
        HttpStatus statusCode = HttpStatus.OK;
        if(users.size()==0){
            message = "No Users Found";
        }
        else{
            message = "List of Users";
        }
        ResponseBodyObj obj = new ResponseBodyObj(users, message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
    
    @GetMapping("{userId}")
    public ResponseEntity<ResponseBodyObj<User>> getUser(@PathVariable int userId){
        User user = userService.getUser(userId);
        HttpStatus statusCode;
        String message;
        if(user==null){
            message = "User not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else{
            message = "User found";
            statusCode = HttpStatus.OK;
        }
        ResponseBodyObj obj = new ResponseBodyObj(user, message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
   
    @PostMapping("")
    public ResponseEntity<ResponseBodyObj<User>> createUser(@RequestBody User user){
        UserEntity u = userService.createUser(user);
        String message;
        HttpStatus statusCode;
        if(!u.isValidUser() && !u.isValidMobile()){
            message = "Username and mobile already exists";
            statusCode = HttpStatus.NOT_ACCEPTABLE;
;        }
        else if(!u.isValidUser()){
            message = "Username already exists";
            statusCode = HttpStatus.NOT_ACCEPTABLE;
        }
        else if(!u.isValidMobile()){
            message = "Mobile Number already exists";
            statusCode = HttpStatus.NOT_ACCEPTABLE;
        }
        else{
            message = "User Created";
            statusCode = HttpStatus.OK;
        }
        ResponseBodyObj obj = new ResponseBodyObj(u.getUser(), message, statusCode);
        return new ResponseEntity<>(obj, statusCode);
    }
    
    @PutMapping("{userId}")
    public ResponseEntity<ResponseBodyObj<User>> updateUser(@PathVariable int userId, @RequestBody User user){
        User u = userService.updateUser(userId, user);
        String message = "";
        HttpStatus statusCode = HttpStatus.OK;
        ResponseBodyObj obj = new ResponseBodyObj(u, message, statusCode);
        return new ResponseEntity<>(obj, statusCode);
    }
    
    @DeleteMapping("{userId}")
    public ResponseEntity<ResponseBodyObj<User>> deleteUser(@PathVariable int userId){
        
        String message;
        HttpStatus statusCode;
        User user = userService.deleteUser(userId);
        if(user == null){
            message = "User not found";
            statusCode = HttpStatus.NOT_FOUND;
        }
        else{
            message = "User deleted Successfully";
            statusCode = HttpStatus.OK;
        }
        ResponseBodyObj obj = new ResponseBodyObj(user, message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
    
    @PostMapping("login")
    public ResponseEntity<ResponseBodyObj<User>> loginUser(@RequestBody User user){
        UserEntity userEntity = userService.loginUser(user);
        String message;
        HttpStatus statusCode;
        if(userEntity.isValidUser() && !userEntity.isNotUser()){
            message = "Logged In successfully";
            statusCode = HttpStatus.OK;
        }
        else if(!userEntity.isValidUser() && !userEntity.isNotUser()){
            message = "Bad Credentials";
            statusCode = HttpStatus.UNAUTHORIZED;
        }
        else{
            message = "User does not exist with username";
            statusCode = HttpStatus.NOT_FOUND;
        }
        ResponseBodyObj obj = new ResponseBodyObj(userEntity.getUser(), message, statusCode);
        return new ResponseEntity(obj, statusCode);
    }
}
