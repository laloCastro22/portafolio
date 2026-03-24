package com.practica.java_api_portfolio.infrastructure.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "verification.code")
public class VerificationCodeProperties {
    private Duration ttl;
    private int maxAttempts;
    private Duration resendCooldown;

}