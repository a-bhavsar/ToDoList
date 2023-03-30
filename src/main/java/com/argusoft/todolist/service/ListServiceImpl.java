/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.service;

import com.argusoft.todolist.dao.ListDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */

@Service
public class ListServiceImpl implements ListService{
    
    @Autowired
    private ListDao listDao;

    @Override
    public List<com.argusoft.todolist.entity.List> getLists(int userId) {
        return listDao.getLists(userId);
    }

    @Override
    public com.argusoft.todolist.entity.List addListToUser(int userId, com.argusoft.todolist.entity.List list) {
        return listDao.addListToUser(userId, list);    
    }

    @Override
    public com.argusoft.todolist.entity.List getSingleList(int userId, int listId) {
        return listDao.getSingleList(userId, listId);
    }

    @Override
    public com.argusoft.todolist.entity.List updateList(int userId, int listId, com.argusoft.todolist.entity.List list) {
        return listDao.updateList(userId, listId, list);
    }

    @Override
    public String deleteList(int userId, int listId) {
        return listDao.deleteList(userId, listId);    
    }
    
}
