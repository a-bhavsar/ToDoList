/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argusoft.todolist.utils;

import org.springframework.http.HttpStatus;

/**
 *
 * @author arpit
 */
public class ResponseBodyObj<T> {
    private T data;
    private String message;
    private HttpStatus statusCode;

    public ResponseBodyObj() {
    }

    public ResponseBodyObj(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseBodyObj(T data, String message, HttpStatus statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
    
    
}
