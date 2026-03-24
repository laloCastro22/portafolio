package com.practica.java_api_portfolio.infrastructure.security;

import com.practica.java_api_portfolio.application.port.out.VerificationCodeGeneratorPort;
import com.practica.java_api_portfolio.domain.model.VerificationCode;

import java.security.SecureRandom;

public class VerificationCodeGenerator implements VerificationCodeGeneratorPort {
    private final SecureRandom secureRandom;

    public VerificationCodeGenerator(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }

    @Override
    public VerificationCode generate() {
        int number = this.secureRandom.nextInt(900000) + 100000;
        return new VerificationCode(String.valueOf(number));
    }
}
