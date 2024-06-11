package com.bkopysc.shortmind.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bkopysc.shortmind.exceptions.ErrorDetails;
import com.bkopysc.shortmind.exceptions.ObjectExistedException;
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;
import com.bkopysc.shortmind.exceptions.WrongPasswordException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleObjectNotFoundException(ObjectNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectExistedException.class)
    public ResponseEntity<ErrorDetails> handleObjectExistedException(ObjectExistedException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorDetails> handleWrongPasswordException(WrongPasswordException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
