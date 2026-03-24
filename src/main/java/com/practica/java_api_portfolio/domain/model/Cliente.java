package com.practica.java_api_portfolio.domain.model;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Cliente {
    private final UUID id;
    private final String nombre;
    private final String email;

    public Cliente(UUID id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente conNombre(String nuevoNombre) {
        return new Cliente(this.id, nuevoNombre, this.email);
    }

    public Cliente conEmail(String nuevoEmail) {
        return new Cliente(this.id, this.nombre, nuevoEmail);
    }
}
