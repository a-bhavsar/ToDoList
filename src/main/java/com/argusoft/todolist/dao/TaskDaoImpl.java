/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.dao;

import com.argusoft.todolist.entity.List;
import com.argusoft.todolist.entity.Task;
import com.argusoft.todolist.entity.User;
import com.argusoft.todolist.repository.ListRepository;
import com.argusoft.todolist.repository.TaskRepository;
import com.argusoft.todolist.repository.UserRepository;
import com.argusoft.todolist.utils.TaskEntity;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arpit
 */
@Service
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean checkUser(int userId, int listId) {
        Optional<User> user = userRepository.findById(userId);
        User theUser = null;
        if (user.isPresent()) {
            theUser = user.get();
        }
        if (theUser == null) {
            return false;
        }
        List theList = null;
        Optional<List> list = listRepository.findById(listId);
        if(list.isPresent()){
            theList = list.get();
            if(theList.getUser().getId() != userId){
                return false;
            }
        }
        return true;
    }

    private boolean checkList(int listId) {
        Optional<List> list = listRepository.findById(listId);
        List theList = null;
        if (list.isPresent()) {
            theList = list.get();
        }
        if (theList == null) {
            return false;
        }
        return true;
    }

    @Override
    public TaskEntity createTask(int userId, int listId, Task task) {
        if (!checkUser(userId, listId)) {
            return new TaskEntity(null, true, false, false);
        }
        Optional<List> list = listRepository.findById(listId);
        List theList = null;
        if (list.isPresent()) {
            theList = list.get();
            if(theList.getUser().getId() != userId){
                return new TaskEntity(null, true, false, false);
            }
        }
        if (theList == null) {
            return new TaskEntity(null, false, true, false);
        } else if (theList.getUser().getId() != userId) {
            return new TaskEntity(null, false, true, false);
        } else {
            java.util.List<Task> tasks = getAllTasks(userId, listId).getTasks();
            for(Task t : tasks){
                if(t.getTitle().equals(task.getTitle())){
                    return new TaskEntity(task, false, false, true);
                }
            }
            task.setList(theList);
            taskRepository.save(task);
            return new TaskEntity(task, false, false, false);
        }
    }

    @Override
    public TaskEntity getAllTasks(int userId, int listId) {
        if (!checkUser(userId, listId)) {
            return new TaskEntity(true, false, null);
        } else if (!checkList(listId)) {
            return new TaskEntity(false, true, null);
        } else {
            List list = listRepository.findById(listId).get();
            if (list.getUser().getId() != userId) {
                return new TaskEntity(false, true, null);
            } else {
                java.util.List<Task> tasks = taskRepository.findAll();
                java.util.List<Task> myTasks = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.getList().getId() == listId) {
                        myTasks.add(task);
                    }
                }
                return new TaskEntity(false, false, myTasks);
            }

        }

    }

    @Override
    public TaskEntity getSingleTask(int userId, int listId, int taskId) {
        if (!checkUser(userId, listId)) {
            return new TaskEntity(null, true, false);
        } else if (!checkList(listId)) {
            return new TaskEntity(null, false, true);
        } else {
            List list = listRepository.findById(listId).get();
            if (list.getUser().getId() != userId) {
                return new TaskEntity(null, false, true);
            } else {
                Optional<Task> task = taskRepository.findById(taskId);
                Task theTask = null;
                if (task.isPresent()) {
                    theTask = task.get();
                }
                if (theTask == null) {
                    return new TaskEntity(null, true, true);
                } else {
                    if (theTask.getList().getId() != listId) {
                        return new TaskEntity(null, true, true);
                    } else {
                        return new TaskEntity(theTask, false, false);
                    }

                }

            }

        }

    }

    @Override
    public TaskEntity updateTask(int userId, int listId, int taskId, Task task) {
        if (!checkUser(userId, listId)) {
            return new TaskEntity(null, true, false, false);
        } 
        else if (!checkList(listId)) {
            return new TaskEntity(null, false, true, false);
        }
        else {
            List list = listRepository.findById(listId).get();
            if (list.getUser().getId() != userId) {
                return new TaskEntity(null, false, true, false);
            } 
            else {
                Optional<Task> t = taskRepository.findById(taskId);
                Task theTask = null;
                if (t.isPresent()) {
                    theTask = t.get();
                }
                if (theTask == null) {
                    return new TaskEntity(null, true, true, false);
                }
                else{
                    if(theTask.getList().getId()!=listId){
                        return new TaskEntity(null, true, true, false);
                    }
                    else{
                        TaskEntity taskEntity = getAllTasks(userId, listId);
                        java.util.List<Task> tasks  = taskEntity.getTasks();
                        for(Task t1 : tasks){
                            if(t1.getTitle().equals(task.getTitle()) && t1.getId() != taskId){
                                return new TaskEntity(theTask, false, false, true);
                            }
                        }
                        theTask.setTitle(task.getTitle());
                        theTask.setDescription(task.getDescription());
                        theTask.setStatus(task.getStatus());
                        theTask.setStartDate(task.getStartDate());
                        theTask.setEndDate(task.getEndDate());
                        taskRepository.save(theTask);
                        return new TaskEntity(theTask, false, false, false);
                    } 
                }
            }

            
        }

    }

    @Override
    public TaskEntity deleteTask(int userId, int listId, int taskId) {
        if (!checkUser(userId, listId)) {
            return new TaskEntity(null, true, false);
        }
        else if (!checkList(listId)) {
            return new TaskEntity(null, false, true);
        }
        else{
            List list = listRepository.findById(listId).get();
            if(list.getUser().getId() != userId){
                return new TaskEntity(null, false, true);
            }
            Optional<Task> task = taskRepository.findById(taskId);
            Task theTask = null;
            if (task.isPresent()) {
                theTask = task.get();
            }
            if (theTask == null) {
                return new TaskEntity(null, true, true);
            }
            else if(theTask.getList().getId() != listId){
                return new TaskEntity(null, true, true);
            }
            else{
                taskRepository.delete(theTask);
                return new TaskEntity(theTask, false, false);
            }
            
        }
        
    }
}
