package com.bkopysc.shortmind.exceptions;

public class UserNotOwnerException extends RuntimeException{

    public UserNotOwnerException(String message) {
        super("User is not the owner of the " + message + " object");
    }
    
}
