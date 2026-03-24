package com.practica.java_api_portfolio.api.dto;


import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String status
) {}