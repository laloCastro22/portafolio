package com.practica.java_api_portfolio.application.service;

import com.practica.java_api_portfolio.application.exception.EmailNotUniqueException;
import com.practica.java_api_portfolio.application.exception.UsernameNotUniqueException;
import com.practica.java_api_portfolio.application.policy.VerificationPolicy;
import com.practica.java_api_portfolio.application.port.in.CrearUserUseCase;
import com.practica.java_api_portfolio.application.port.out.*;
import com.practica.java_api_portfolio.domain.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements CrearUserUseCase {
    final private UserEntityRepositoryPort userEntityRepositoryPort;
    final private EmailVerificationRepositoryPort emailVerificationRepositoryPort;
    final private VerificationPolicy verificationPolicy;
    final private PasswordHasherPort passwordHasherPort;
    final private VerificationCodeGeneratorPort verificationCodeGeneratorPort;
    final private TimeProviderPort time;
    final private EmailSenderPort senderPort;

    @Override
    public Usuario crear(CrearUsuario crearUsuario) {
        Username usernameVO = new Username(crearUsuario.username());
        var isUniqueUsername = userEntityRepositoryPort.existsByUsername(usernameVO);
        if (isUniqueUsername) {
            log.warn("El username {} ya existe", usernameVO.value());
            throw new UsernameNotUniqueException("El Nombre de Usuario: " + usernameVO.value() + " ya se encuentra asociado");
        }
        Email emailVO = new Email(crearUsuario.email());
        var isUniqueEmail = userEntityRepositoryPort.existsByEmail(emailVO);
        if (isUniqueEmail) {
            log.warn("El email: {} ya existe", emailVO.value());
            throw new EmailNotUniqueException("El Email: " + emailVO.value() + " ya se encuentra asociado");
        }
        PasswordHash hashedPassword = passwordHasherPort.hash(crearUsuario.password());
        // Si  son unicos, entonces debemos guardar la informacion del usuario y debemos guardar la logica de verificacion (expiresAt, ...)
        VerificationCode codeGenerated = verificationCodeGeneratorPort.generate();
        Instant lastSentAt = time.now();
        Instant expiresAt = lastSentAt.plus(verificationPolicy.codeTtl());
        int attempts = verificationPolicy.maxAttempts();
        Usuario usuario = userEntityRepositoryPort.save(new Usuario(null,usernameVO,emailVO, hashedPassword, Role.USER, AccountStatus.PENDING_VERIFICATION, null));
        EmailVerification emailVerification = new EmailVerification(usuario.getId(), codeGenerated, expiresAt, attempts, lastSentAt);
        emailVerificationRepositoryPort.save(emailVerification);

        senderPort.sendVerificationCode(emailVO,codeGenerated);

        return  usuario;
    }
}
