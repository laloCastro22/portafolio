package com.practica.java_api_portfolio.api.controller;

import com.practica.java_api_portfolio.api.dto.CrearUsuarioRequest;
import com.practica.java_api_portfolio.api.dto.UserResponse;
import com.practica.java_api_portfolio.api.mapper.UserApiMapper;
import com.practica.java_api_portfolio.application.port.in.CrearUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(("/api/v1/usuarios"))
@RequiredArgsConstructor
public class UsuarioController {
    private final CrearUserUseCase crearUser;
    private final UserApiMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> creaUsuario(@RequestBody @Valid CrearUsuarioRequest userRequest) {
        var usuario = crearUser.crear(new CrearUserUseCase.CrearUsuario(userRequest.userName(), userRequest.email(), userRequest.password()));
        var response = mapper.toResponse(usuario);
        return ResponseEntity.status(CREATED).body(response);
    }
}
