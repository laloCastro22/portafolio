package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepositoryPort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(UUID id);
}
