package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.VerificationCode;

public interface VerificationCodeHasherPort {
    String hash(VerificationCode code);
    boolean matches(VerificationCode code, String storedHash);
}
