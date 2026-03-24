package com.practica.java_api_portfolio.api.controller;

import com.practica.java_api_portfolio.api.dto.ClienteResponse;
import com.practica.java_api_portfolio.api.dto.CrearClienteRequest;
import com.practica.java_api_portfolio.api.mapper.ClienteApiMapper;
import com.practica.java_api_portfolio.application.port.in.CrearClienteUseCase;
import com.practica.java_api_portfolio.application.port.in.ObtenerClienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final CrearClienteUseCase crearCliente;
    private final ObtenerClienteUseCase obtenerCliente;
    private final ClienteApiMapper mapper;

    public ClienteController(CrearClienteUseCase crearCliente,
                             ObtenerClienteUseCase obtenerCliente,
                             ClienteApiMapper mapper) {
        this.crearCliente = crearCliente;
        this.obtenerCliente = obtenerCliente;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse crear(@Valid @RequestBody CrearClienteRequest request) {
        var creado = crearCliente.crear(
                new CrearClienteUseCase.CrearClienteCommand(request.nombre(), request.email())
        );
        return mapper.toResponse(creado);
    }

    @GetMapping("/{value}")
    public ClienteResponse obtener(@PathVariable UUID id) {
        var cliente = obtenerCliente.obtenerPorId(id);
        return mapper.toResponse(cliente);
    }
}
