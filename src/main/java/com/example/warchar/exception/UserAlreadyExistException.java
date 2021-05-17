package com.example.warchar.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(){

    }
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
