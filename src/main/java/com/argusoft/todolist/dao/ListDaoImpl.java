/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.UserRepository;
import jakarta.persistence.EntityManager;
import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.repository.ListRepository;
import com.argusoft.todolist.utils.ListEntity;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */

@Service
public class ListDaoImpl implements ListDao{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private ListRepository listRepository;
    
    private boolean checkUser(int userId){
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        if(theUser==null){
            return false;
        }
        return true;
    }
    

    @Override
    public java.util.List<List> getLists(int userId) {
        if(!checkUser(userId)){
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        java.util.List<List> lists = listRepository.findAll();
        java.util.List<List> myLists = new ArrayList<>();
        for(List l : lists){
            System.out.println("List is " + l);
            if(l.getUser().getId() == userId){
                myLists.add(l);
            }
        }
        return myLists;
    }

    @Override
    @Transactional
    public List addListToUser(int userId, List list) {
        if(!checkUser(userId)){
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        return listRepository.save(new List(list.getTitle(), 
                list.getDescription(), 0, theUser));        
    }

    @Override
    public ListEntity getSingleList(int userId, int listId) {
        boolean isUser;
        if(!checkUser(userId)){
            isUser = true;
            return new ListEntity(null, true);
        }
        Optional<List> list = listRepository.findById(listId);
        List theList = null;
        if(list.isPresent()){
            theList = list.get();
            System.out.println("list is" + list);
            if(theList.getUser().getId() == userId){
               return new ListEntity(theList, false);
            }
            else{
                return new ListEntity(null, false);
            }
        }
        
        return new ListEntity(theList, false);
    }

    @Override
    @Transactional
    public ListEntity updateList(int userId, int listId, List list) {
        if(!checkUser(userId)){
            return new ListEntity(null, true);
        }
        Optional<List> l = listRepository.findById(listId);
        List theList = null;
        if(l.isPresent()){
            theList = l.get();
            theList.setTitle(list.getTitle());
            theList.setDescription(list.getDescription());
            if(theList.getUser().getId() == userId){
                listRepository.save(theList);
                return new ListEntity(theList, false);
            }
            else{
                return new ListEntity(null, false);
            }
        }
        return new ListEntity(theList, false);
    }

    @Override
    @Transactional
    public ListEntity deleteList(int userId, int listId) {
       if(!checkUser(userId)){
           return new ListEntity(null, true);
       }
       Optional<List> l = listRepository.findById(listId);
       List list = null;
       if(l.isPresent()){
           list = l.get();
           listRepository.delete(list);
           return new ListEntity(list, false);
       }
       else{
           return new ListEntity(null, false);
       }   
    }
    
}
