package com.practica.java_api_portfolio.domain.model;

public record Email(String value) {

    public Email {
        String normalized = normalize(value);
        validate(normalized);
        value = normalized;
    }

    private static String normalize(String raw) {
        if (raw == null) return null;
        return raw.trim().toLowerCase();
    }

    private static void validate(String v) {
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException("email vacío");
        }
        if (!v.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$")) {
            throw new IllegalArgumentException("email inválido");
        }
    }
}