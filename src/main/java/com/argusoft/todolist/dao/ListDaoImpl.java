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
import jakarta.transaction.Transactional;
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

    @Override
    public java.util.List<List> getLists(int userId) {
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        java.util.List<List> lists = theUser.getLists();
        return lists;
    }

    @Override
    @Transactional
    public List addListToUser(int userId, List list) {
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
//        list.setUser(theUser);
//        System.out.println("Hii I am testing Here"+theUser.getLists());
//        theUser.getLists().add(list);
//        userRepository.save(theUser);
        List l = new List();
        l.setTitle(list.getTitle());
        l.setDescription(list.getDescription());
        l.setUser(theUser);
        entityManager.persist(l);
        return list;

//        int rows = entityManager
//                .createQuery("insert into list(title, description, user_id) values(:title, :description, :userId)")
//                .setParameter("title", list.getTitle())
//                .setParameter("description", list.getDescription())
//                .setParameter("userId", userId)
//                .executeUpdate();
//        if(rows==0){
//            return null;
//        }
//        return list;
        
    }

    @Override
    public List getSingleList(int userId, int listId) {
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if(user.isPresent()){
            theUser = user.get();
        }
        java.util.List<List> lists = theUser.getLists();
        for(List l : lists){
            if(l.getId() == listId){
                return l;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public List updateList(int userId, int listId, List list) {
//        Optional<User> user = userRepository.findById(userId);
//        User theUser = null;
//        if(user.isPresent()){
//            theUser = user.get();
//        }
//        java.util.List<List> lists = theUser.getLists();    
//        for(List l:lists){
//            if(l.getId()==listId){
//                list.setId(listId);
//                list.setUser(theUser);
//                lists.remove(l);
//                lists.add(list);
//                entityManager.merge(list); 
//                return list;
//            }
//        }
//        return null;
        List l = getSingleList(userId, listId);
        if(l==null){
            return null;
        }
        l.setTitle(list.getTitle());
        l.setDescription(list.getDescription());
        entityManager.persist(l);
        return l;
    }

    @Override
    @Transactional
    public String deleteList(int userId, int listId) {
        List l = getSingleList(userId, listId);
        if(l==null){
            return "List does not exists";
        }
        listRepository.delete(l);
        return "List deleted with id " + listId;

        
    }
    
}
