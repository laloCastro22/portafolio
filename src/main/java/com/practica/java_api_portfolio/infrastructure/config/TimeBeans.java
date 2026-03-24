package com.practica.java_api_portfolio.infrastructure.config;

import com.practica.java_api_portfolio.application.port.out.TimeProviderPort;
import com.practica.java_api_portfolio.infrastructure.time.SystemTimeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeBeans {
    @Bean
    TimeProviderPort timeProvider() { return new SystemTimeProvider(); }
}
