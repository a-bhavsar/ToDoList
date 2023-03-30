/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */

@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        Optional<User> u1 = userRepository.findById(userId);
        User user = null;
        if(u1.isPresent()){
            user = u1.get();
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        User u1 = userRepository.save(new User(user.getUsername(), user.getPassword(), user.getMobileNo()));
        return u1;
    }

    @Override
    public User updateUser(int userId, User user) {
        Optional<User> u1 = userRepository.findById(userId);
        User u = null;
        if(u1.isPresent()){
            u = u1.get();
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            u.setMobileNo(user.getMobileNo());
            return userRepository.save(u);
        }
        return u;
    }

    @Override
    public String deleteUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        userRepository.deleteById(userId);
        if(theUser!=null){
            return "User deleted with Id : " + userId;   
        }
        return "User does not exists";
        
    }
    
}
