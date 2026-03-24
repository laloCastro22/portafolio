package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.Email;
import com.practica.java_api_portfolio.domain.model.Username;
import com.practica.java_api_portfolio.domain.model.Usuario;

public interface UserEntityRepositoryPort {
    Usuario save(Usuario usuario);
    boolean existsByUsername(Username username);
    boolean existsByEmail(Email email);
}
