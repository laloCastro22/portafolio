package com.practica.java_api_portfolio.application.port.in;

import com.practica.java_api_portfolio.domain.model.Cliente;

public interface CrearClienteUseCase {
    Cliente crear(CrearClienteCommand command);

    record CrearClienteCommand(String nombre, String email) {}
}