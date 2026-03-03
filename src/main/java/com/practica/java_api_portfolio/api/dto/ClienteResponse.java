package com.practica.java_api_portfolio.api.dto;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nombre,
        String email
) {}