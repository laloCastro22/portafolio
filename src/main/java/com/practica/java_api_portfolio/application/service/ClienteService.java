package com.practica.java_api_portfolio.application.service;


import com.practica.java_api_portfolio.application.port.in.CrearClienteUseCase;
import com.practica.java_api_portfolio.application.port.in.ObtenerClienteUseCase;
import com.practica.java_api_portfolio.application.port.out.ClienteRepositoryPort;
import com.practica.java_api_portfolio.domain.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService implements CrearClienteUseCase, ObtenerClienteUseCase {

    private final ClienteRepositoryPort repository;



    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Cliente crear(CrearClienteCommand command) {
        Cliente nuevo = new Cliente(UUID.randomUUID(), command.nombre(), command.email());
        return repository.save(nuevo);
    }

    @Override
    public Cliente obtenerPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
    }
}
