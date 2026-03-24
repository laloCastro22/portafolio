package com.practica.java_api_portfolio.infrastructure.persistence.mapper;

import com.practica.java_api_portfolio.domain.model.Email;
import com.practica.java_api_portfolio.domain.model.EmailVerification;
import com.practica.java_api_portfolio.domain.model.PasswordHash;
import com.practica.java_api_portfolio.domain.model.Usuario;
import com.practica.java_api_portfolio.domain.model.UsuarioId;
import com.practica.java_api_portfolio.domain.model.Username;
import com.practica.java_api_portfolio.domain.model.VerificationCode;
import com.practica.java_api_portfolio.infrastructure.persistence.entity.EmailVerificationEntity;
import com.practica.java_api_portfolio.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default Usuario toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Usuario(
                toUsuarioId(entity.getId()),
                toUsername(entity.getUsername()),
                toEmail(entity.getEmail()),
                toPasswordHash(entity.getPasswordHash()),
                entity.getRole(),
                entity.getStatus(),
                null
        );
    }

    default UsuarioId toUsuarioId(UUID value) {
        return value == null ? null : new UsuarioId(value);
    }

    default Username toUsername(String value) {
        return value == null ? null : new Username(value);
    }

    default Email toEmail(String value) {
        return value == null ? null : new Email(value);
    }

    default PasswordHash toPasswordHash(String value) {
        return value == null ? null : new PasswordHash(value);
    }

    default EmailVerification toEmailVerification(EmailVerificationEntity entity) {
        if (entity == null) {
            return null;
        }

        return new EmailVerification(
                toUsuarioId(entity.getUser().getId()),
                toVerificationCode(entity.getCodeHash()),
                entity.getExpiresAt(),
                entity.getAttempts(),
                entity.getLastSentAt()
        );
    }

    default VerificationCode toVerificationCode(String value) {
        return value == null ? null : new VerificationCode(value);
    }

    @Mapping(target = "username", expression = "java(usuario.getUsername().value())")
    @Mapping(target = "email", expression = "java(usuario.getEmail().value())")
    @Mapping(target = "passwordHash", expression = "java(usuario.getPasswordHash().value())")
    @Mapping(target = "role", expression = "java(usuario.getRole())")
    @Mapping(target = "status", expression = "java(usuario.getStatus())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emailVerification", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(Usuario usuario);

    @Mapping(target = "codeHash", expression = "java(verification.getCode().value())")
    @Mapping(target = "expiresAt", expression = "java(verification.getExpiresAt())")
    @Mapping(target = "attempts", expression = "java(verification.getAttempts())")
    @Mapping(target = "lastSentAt", expression = "java(verification.getLastSentAt())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    EmailVerificationEntity toEntityVerificationCode(EmailVerification verification);
}