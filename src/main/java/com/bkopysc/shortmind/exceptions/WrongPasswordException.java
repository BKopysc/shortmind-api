package com.bkopysc.shortmind.exceptions;

public class WrongPasswordException extends RuntimeException{
        
        public WrongPasswordException() {
            super("Wrong password");
        }
    
}
