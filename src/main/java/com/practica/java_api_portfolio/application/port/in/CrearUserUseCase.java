package com.practica.java_api_portfolio.application.port.in;


import com.practica.java_api_portfolio.domain.model.Usuario;

public interface CrearUserUseCase {
    Usuario crear(CrearUsuario crearUsuario);

    record CrearUsuario(String username, String email, String password) {}
}
