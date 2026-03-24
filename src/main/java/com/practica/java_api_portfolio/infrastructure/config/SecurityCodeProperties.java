package com.practica.java_api_portfolio.infrastructure.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "security.verification")
public class SecurityCodeProperties {
    private String hmacSecret;
}