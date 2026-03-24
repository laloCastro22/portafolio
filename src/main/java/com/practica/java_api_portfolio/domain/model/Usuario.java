package com.practica.java_api_portfolio.domain.model;

import lombok.Getter;

@Getter
public class Usuario {
    final private UsuarioId id;
    final private Username username;
    final private Email email;
    final private PasswordHash passwordHash;
    final private Role role;
    final private AccountStatus status;
    final private EmailVerification verificacion;


    public Usuario(UsuarioId id, Username username, Email email, PasswordHash passwordHash, Role role, AccountStatus status, EmailVerification verificacion) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = status;
        this.verificacion = verificacion;
    }
    public boolean matches(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("El imput es vacio, virifique la informacion");
        }
        return input.equals(this.username.value());
    }

}


