package com.practica.java_api_portfolio.infrastructure.persistence.adapter;

import com.practica.java_api_portfolio.application.port.out.EmailVerificationRepositoryPort;
import com.practica.java_api_portfolio.domain.model.EmailVerification;
import com.practica.java_api_portfolio.domain.model.UsuarioId;
import com.practica.java_api_portfolio.infrastructure.persistence.entity.EmailVerificationEntity;
import com.practica.java_api_portfolio.infrastructure.persistence.entity.UserEntity;
import com.practica.java_api_portfolio.infrastructure.persistence.mapper.UserMapper;
import com.practica.java_api_portfolio.infrastructure.persistence.repository.EmailVerificationEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationRepositoryAdapter implements EmailVerificationRepositoryPort {
    private final EmailVerificationEntityRepository entityRepository;
    private final UserMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EmailVerification save(EmailVerification verification) {
        EmailVerificationEntity verificationEntity = mapper.toEntityVerificationCode(verification);
        UserEntity userRef = entityManager.getReference(
                UserEntity.class,
                verification.getOwner().value()
        );
        verificationEntity.setUser(userRef);
        EmailVerificationEntity saved = entityRepository.save(verificationEntity);

        return mapper.toEmailVerification(saved);
    }

    @Override
    public EmailVerification findByUserId(UsuarioId userId) {
        return null;
    }
}
