package com.practica.java_api_portfolio.infrastructure.persistence.adapter;

import com.practica.java_api_portfolio.application.port.out.UserEntityRepositoryPort;
import com.practica.java_api_portfolio.domain.model.Email;
import com.practica.java_api_portfolio.domain.model.Username;
import com.practica.java_api_portfolio.domain.model.Usuario;
import com.practica.java_api_portfolio.infrastructure.persistence.entity.UserEntity;
import com.practica.java_api_portfolio.infrastructure.persistence.mapper.UserMapper;
import com.practica.java_api_portfolio.infrastructure.persistence.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserEntityRepositoryPort {
    private final UserEntityRepository repository;
    private final UserMapper mapper;

    @Override
    public Usuario save(Usuario usuario) {
        UserEntity user = mapper.toEntity(usuario);
        UserEntity userSaved = repository.save(user);

        return mapper.toDomain(userSaved);
    }

    @Override
    public boolean existsByUsername(Username username) {
        return repository.existsByUsername(username.value());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repository.existsByEmail(email.value());
    }
}
