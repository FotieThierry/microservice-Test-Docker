package com.example.personneservice.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.OperationNotSupportedException;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleException(EntityNotFoundException exception){
          return  new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException exception){
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationNotSupportedException.class)
    public ResponseEntity<String> handleException(OperationNotSupportedException exception){
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
