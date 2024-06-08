package com.bkopysc.shortmind.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    
    public ObjectNotFoundException(String objectName) {
        super(objectName + " not found");
    }
}
