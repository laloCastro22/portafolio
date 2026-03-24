package com.practica.java_api_portfolio.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "security.encoder")
public class SecurityEncoderProperties {
    private int bcryptStrength;
}
