package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.VerificationCode;

public interface VerificationCodeGeneratorPort {
    VerificationCode generate();
}
