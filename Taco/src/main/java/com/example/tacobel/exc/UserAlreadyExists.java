package com.example.tacobel.exc;


public class UserAlreadyExists extends Exception{

    public UserAlreadyExists(String message){
        super(message);
    }
}
