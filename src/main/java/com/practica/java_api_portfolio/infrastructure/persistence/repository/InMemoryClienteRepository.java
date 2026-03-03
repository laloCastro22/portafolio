package com.practica.java_api_portfolio.infrastructure.persistence.repository;

import com.practica.java_api_portfolio.application.port.out.ClienteRepositoryPort;
import com.practica.java_api_portfolio.domain.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryClienteRepository implements ClienteRepositoryPort {

    private final ConcurrentHashMap<UUID, Cliente> store = new ConcurrentHashMap<>();

    @Override
    public Cliente save(Cliente cliente) {
        store.put(cliente.getId(), cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}