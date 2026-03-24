package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.PasswordHash;

public interface PasswordHasherPort {
    PasswordHash hash(String rawPassword);

    boolean matches(String rawPassword, PasswordHash hash);
}
