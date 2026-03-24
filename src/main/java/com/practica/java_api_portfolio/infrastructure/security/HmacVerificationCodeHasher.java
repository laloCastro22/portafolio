package com.practica.java_api_portfolio.infrastructure.security;

import com.practica.java_api_portfolio.application.port.out.VerificationCodeHasherPort;
import com.practica.java_api_portfolio.domain.model.VerificationCode;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class HmacVerificationCodeHasher implements VerificationCodeHasherPort {

    private final byte[] secretBytes;

    public HmacVerificationCodeHasher(byte[] secretBytes) {
        this.secretBytes = secretBytes;
    }


    @Override
    public String hash(VerificationCode code) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacSHA256");
            mac.init(keySpec);

            byte[] hmac = mac.doFinal(code.value().getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmac);
        } catch (Exception e) {
            throw new IllegalStateException("No fue posible calcular el HMAC del código", e);
        }
    }

    @Override
    public boolean matches(VerificationCode code, String storedHash) {
        String calculated = hash(code);
        return calculated.equals(storedHash);
    }
}