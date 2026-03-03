package com.practica.java_api_portfolio.api.mapper;

import com.practica.java_api_portfolio.api.dto.ClienteResponse;
import com.practica.java_api_portfolio.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteApiMapper {
    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getEmail());
    }
}
