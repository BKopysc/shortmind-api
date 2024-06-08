package com.bkopysc.shortmind.exceptions;

public class ObjectExistedException extends RuntimeException{
    
    public ObjectExistedException(String objectName) {
        super(objectName + " already existed");
    }
}
