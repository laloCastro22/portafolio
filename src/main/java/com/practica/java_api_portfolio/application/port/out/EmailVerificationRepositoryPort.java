package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.EmailVerification;
import com.practica.java_api_portfolio.domain.model.UsuarioId;

public interface EmailVerificationRepositoryPort {
    EmailVerification save(EmailVerification verification);
    EmailVerification findByUserId(UsuarioId userId);

}
