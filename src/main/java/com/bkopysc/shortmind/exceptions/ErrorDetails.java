package com.bkopysc.shortmind.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDetails {
    
    private String message;
    private HttpStatus status;

    
}
