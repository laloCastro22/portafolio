package com.practica.java_api_portfolio.domain.model;

import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Modelo de dominio: verificación de correo.
 * - Inmutable.
 * - No usa Instant.now() (el "now" entra por parámetro).
 * - No hardcodea maxAttempts/cooldown/ttl (eso viene de application/policy).
 */
@Getter
public final class EmailVerification {

    private final UsuarioId owner;
    private final VerificationCode code;
    private final Instant expiresAt;
    private final int attempts;
    private final Instant lastSentAt;

    public EmailVerification(UsuarioId owner, VerificationCode code, Instant expiresAt, int attempts, Instant lastSentAt) {
        this.owner = owner;
        this.code = Objects.requireNonNull(code, "code no puede ser null");
        this.expiresAt = Objects.requireNonNull(expiresAt, "expiresAt no puede ser null");
        this.lastSentAt = Objects.requireNonNull(lastSentAt, "lastSentAt no puede ser null");

        if (attempts < 0) {
            throw new IllegalArgumentException("attempts no puede ser negativo");
        }
        this.attempts = attempts;
    }

    public boolean isExpired(Instant now) {
        Objects.requireNonNull(now, "now no puede ser null");
        return now.isAfter(expiresAt);
    }

    /**
     * Comparación del código ingresado vs el código esperado.
     * Decide aquí si permites espacios; se normaliza con trim().
     */
    public boolean matches(String input) {
        if (input == null) return false;
        return code.matches(input);
    }

    /**
     * Registra un intento fallido.
     * - Retorna una nueva instancia con attempts incrementado.
     * - Si supera el máximo permitido, lanza una excepción de dominio.
     */
    public EmailVerification registerFailedAttempt(int maxAttempts) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("maxAttempts debe ser > 0");
        }
        int newAttempts = this.attempts + 1;

        if (newAttempts > maxAttempts) {
            throw new VerificationAttemptsExceeded("Se excedió el número máximo de intentos");
        }

        return new EmailVerification(this.owner, this.code, this.expiresAt, newAttempts, this.lastSentAt);
    }

    /**
     * Reenvío de código:
     * - aplica cooldown (anti-spam)
     * - reinicia intentos (opcional: aquí los reiniciamos a 0)
     * - recalcula expiresAt = now + ttl
     */
    public EmailVerification resend(VerificationCode newCode, Instant now, Duration cooldown, Duration ttl) {
        Objects.requireNonNull(newCode, "newCode no puede ser null");
        Objects.requireNonNull(now, "now no puede ser null");
        Objects.requireNonNull(cooldown, "cooldown no puede ser null");
        Objects.requireNonNull(ttl, "ttl no puede ser null");

        if (cooldown.isNegative()) {
            throw new IllegalArgumentException("cooldown no puede ser negativo");
        }
        if (ttl.isZero() || ttl.isNegative()) {
            throw new IllegalArgumentException("ttl debe ser > 0");
        }

        Instant nextAllowed = lastSentAt.plus(cooldown);
        if (now.isBefore(nextAllowed)) {
            throw new ResendTooSoon("Aún no se puede reenviar el código");
        }

        Instant newExpiresAt = now.plus(ttl);
        return new EmailVerification(this.owner, newCode, newExpiresAt, 0, now);
    }


    // Excepciones de dominio (mejor que IllegalArgumentException para reglas)
    public static class VerificationAttemptsExceeded extends RuntimeException {
        public VerificationAttemptsExceeded(String message) { super(message); }
    }

    public static class ResendTooSoon extends RuntimeException {
        public ResendTooSoon(String message) { super(message); }
    }
}