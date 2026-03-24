package com.practica.java_api_portfolio.domain.model;


/**
 * Value Object: código de verificación.
 * - Encapsula normalización y comparación.
 */
public record VerificationCode(String value) {

    public VerificationCode {
        if (value == null) throw new IllegalArgumentException("code no puede ser null");

        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("code no puede estar vacío");
        }
        if (!normalized.matches("^\\d{6}$")) {
            throw new IllegalArgumentException("code debe ser numérico de 6 dígitos");
        }
        value = normalized;
    }

    public boolean matches(String input) {
        if (input == null) return false;
        return this.value.equals(input.trim());
    }
}