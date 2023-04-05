/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.utils.UserEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */
public interface UserDao {
    public java.util.List<User> getAllUser();
    public User getUser(int userId);
    public UserEntity createUser(User user);
    public User updateUser(int userId, User user);
    public User deleteUser(int userId);
    public UserEntity loginUser(User user);
}
