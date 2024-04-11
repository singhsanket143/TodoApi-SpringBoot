package com.example.todoapispring.Model;

public class ResponseModel {
    public boolean success;
    public String message;
    public Object data;

    public ResponseModel(boolean success, String msg) {
        this.success = success;
        this.message = msg;
    }
    public  ResponseModel(boolean success, Object data)
    {
        this.success = success;
        this.data = data;
    }
}
