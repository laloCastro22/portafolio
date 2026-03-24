package com.practica.java_api_portfolio.domain.model;

public record PasswordHash(String value) {

    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("passwordHash vacío");
        }
        if (!value.startsWith("$2")) {
            throw new IllegalArgumentException("passwordHash no parece un hash válido");
        }
    }
}