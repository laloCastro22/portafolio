package com.practica.java_api_portfolio.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CrearClienteRequest(
        @NotBlank String nombre,
        @NotBlank @Email String email
) {}