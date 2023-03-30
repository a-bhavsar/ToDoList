/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */
public interface UserDao {
    public java.util.List<User> getAllUser();
    public User getUser(int userId);
    public User createUser(User user);
    public User updateUser(int userId, User user);
    public String deleteUser(int userId);
}
