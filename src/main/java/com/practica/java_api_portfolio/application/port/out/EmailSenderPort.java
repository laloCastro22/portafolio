package com.practica.java_api_portfolio.application.port.out;

import com.practica.java_api_portfolio.domain.model.Email;
import com.practica.java_api_portfolio.domain.model.VerificationCode;

public interface EmailSenderPort {
    void sendVerificationCode(Email to, VerificationCode code);
}
