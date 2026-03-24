package com.practica.java_api_portfolio.application.policy;

import java.time.Duration;

public record VerificationPolicy(Duration codeTtl, int maxAttempts, Duration resendCooldown) {
}
