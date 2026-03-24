package com.practica.java_api_portfolio.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequest(
        @NotBlank String userName,
        @NotBlank @Email String email,
        @NotBlank String password
) {}
