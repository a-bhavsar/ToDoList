/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argusoft.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.argusoft.todolist.entity.List;
import org.springframework.stereotype.Repository;


@Repository

public interface ListRepository extends JpaRepository<List, Integer> {
    
}
