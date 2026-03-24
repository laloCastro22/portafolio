package com.practica.java_api_portfolio.domain.model;

import java.util.UUID;

public record UsuarioId(UUID value) {
    static final UUID ZERO = new UUID(0L,0L);
    public UsuarioId {
        validate(value);
    }
    private static void validate(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("El UUID no puede ser nulo.");
        }
        if (value.equals(ZERO)) {
            throw new IllegalArgumentException("El UUID no puede ser vacío (0000...).");
        }
    }
}
