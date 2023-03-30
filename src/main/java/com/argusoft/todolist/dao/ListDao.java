/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */

public interface ListDao {
    public java.util.List<List> getLists(int userId);
    public List addListToUser(int userId, List list);
    public List getSingleList(int userId, int listId);
    public List updateList(int userId, int listId, List list);
    public String deleteList(int userId, int listId);
}
