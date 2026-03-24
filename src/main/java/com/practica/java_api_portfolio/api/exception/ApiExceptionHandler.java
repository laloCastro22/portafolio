package com.practica.java_api_portfolio.api.exception;

import com.practica.java_api_portfolio.api.dto.Error;
import com.practica.java_api_portfolio.application.exception.EmailNotUniqueException;
import com.practica.java_api_portfolio.application.exception.UsernameNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> internalError(Exception ex) {
        var error = new Error(ex.getMessage());
        return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> inputNoValid(IllegalArgumentException ex) {
        var error = new Error(ex.getMessage());
        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsernameNotUniqueException.class)
    public final ResponseEntity<Error> usernameNotUnique(UsernameNotUniqueException ex) {
        var error = new Error(ex.getMessage());
        return new ResponseEntity<Error>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EmailNotUniqueException.class)
    public final ResponseEntity<Error> emailNotUnique(EmailNotUniqueException ex) {
        var error = new Error(ex.getMessage());
        return new ResponseEntity<Error>(error, HttpStatus.CONFLICT);
    }
}