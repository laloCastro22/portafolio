package com.practica.java_api_portfolio.application.port.out;

import java.time.Instant;

public interface TimeProviderPort {
    Instant now();
}
