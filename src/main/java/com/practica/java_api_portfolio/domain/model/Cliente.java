package com.practica.java_api_portfolio.domain.model;
import java.util.UUID;

public class Cliente {
    private final UUID id;
    private final String nombre;
    private final String email;

    public Cliente(UUID id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public Cliente conNombre(String nuevoNombre) {
        return new Cliente(this.id, nuevoNombre, this.email);
    }

    public Cliente conEmail(String nuevoEmail) {
        return new Cliente(this.id, this.nombre, nuevoEmail);
    }
}
