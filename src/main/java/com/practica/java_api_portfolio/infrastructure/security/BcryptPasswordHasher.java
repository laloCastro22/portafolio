package com.practica.java_api_portfolio.infrastructure.security;

import com.practica.java_api_portfolio.application.port.out.PasswordHasherPort;
import com.practica.java_api_portfolio.domain.model.PasswordHash;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BcryptPasswordHasher implements PasswordHasherPort {

    private final PasswordEncoder encoder;

    public BcryptPasswordHasher(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public PasswordHash hash(String rawPassword) {
        validate(rawPassword);
        String passEncoded = this.encoder.encode(rawPassword);
        return new PasswordHash(passEncoded);
    }

    @Override
    public boolean matches(String rawPassword, PasswordHash hash) {
        validate(rawPassword);
        if (hash == null) {
            throw new IllegalArgumentException("No es posible leer el hash");
        }
        return this.encoder.matches(rawPassword, hash.value());
    }

    public void validate(String hash){
        if (hash == null || hash.isBlank()) {
            throw new IllegalArgumentException("No es posible leer el password.");
        }
    }
}
