package com.practica.java_api_portfolio.infrastructure.time;

import com.practica.java_api_portfolio.application.port.out.TimeProviderPort;

import java.time.Instant;

public class SystemTimeProvider implements TimeProviderPort {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
