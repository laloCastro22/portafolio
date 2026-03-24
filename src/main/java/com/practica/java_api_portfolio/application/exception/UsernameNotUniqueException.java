package com.practica.java_api_portfolio.application.exception;

public class UsernameNotUniqueException extends RuntimeException {
    public UsernameNotUniqueException(String message) {
        super(message);
    }
}
