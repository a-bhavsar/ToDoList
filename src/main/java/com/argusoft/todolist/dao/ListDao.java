/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.utils.ListEntity;

/**
 *
 * @author arpit
 */

public interface ListDao {
    public java.util.List<List> getLists(int userId);
    public List addListToUser(int userId, List list);
    public ListEntity getSingleList(int userId, int listId);
    public ListEntity updateList(int userId, int listId, List list);
    public ListEntity deleteList(int userId, int listId);
}
