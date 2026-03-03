package com.practica.java_api_portfolio.application.port.in;

import com.practica.java_api_portfolio.domain.model.Cliente;

import java.util.UUID;

public interface ObtenerClienteUseCase {
    Cliente obtenerPorId(UUID id);
}