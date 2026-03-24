package com.practica.java_api_portfolio.domain.model;
public record Username(String value) {

    public Username {
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
            throw new IllegalArgumentException("username vacío");
        }
        if (v.length() < 3 || v.length() > 30) {
            throw new IllegalArgumentException("username debe tener 3-30 caracteres");
        }
        if (!v.matches("^[a-z0-9._]+$")) {
            throw new IllegalArgumentException("username solo permite letras, números, '.' y '_'");
        }
        if (v.startsWith(".") || v.startsWith("_")) {
            throw new IllegalArgumentException("username no puede iniciar con '.' o '_'");
        }
    }
}