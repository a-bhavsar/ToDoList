/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import com.argusoft.todolist.utils.UserEntity;
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
    public UserEntity createUser(User user) {
        
        List<User> users = getAllUser();
        for(User u : users){
            if(u.getUsername().equals(user.getUsername()) && u.getMobileNo().equals(user.getMobileNo())){
                return new UserEntity(user, false, false, false);
            }
            else if(u.getUsername().equals(user.getUsername())){
                return new UserEntity(user, false, false, true);
            }
            else if(u.getMobileNo().equals(user.getMobileNo()))
            {
                return new UserEntity(user, true, false, false);
            }
        }
        System.out.println("Why this error");
        User u1 = userRepository.save(new User(user.getUsername(), user.getPassword(), user.getMobileNo()));
        System.out.println(u1);
        return new UserEntity(u1, true, false, true);
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
    public User deleteUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        userRepository.deleteById(userId);
        return theUser;
        
    }
    
    @Override
    public UserEntity loginUser(User user){
        java.util.List<User> users = userRepository.findAll();
        for(User u : users){
            if(u.getUsername().equals(user.getUsername())){
                if(u.getPassword().equals(user.getPassword())){
                    System.out.println("Logged in user is " + u);
                    return new UserEntity(u, true, false);
                }
                else{
                    return new UserEntity(user, false, false);
                }
            }
        }
        return new UserEntity(user, false, true);
    }
    
}
