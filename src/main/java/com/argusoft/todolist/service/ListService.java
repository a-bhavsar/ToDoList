/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.service;

import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.utils.ListEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */


public interface ListService {
    public java.util.List<List> getLists(int userId);
    public ListEntity addListToUser(int userId, List list);
    public ListEntity getSingleList(int userId, int listId);
    public ListEntity updateList(int userId, int listId, List list);
    public ListEntity deleteList(int userId, int listId);
}
