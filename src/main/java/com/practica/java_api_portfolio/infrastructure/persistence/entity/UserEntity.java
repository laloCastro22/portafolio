package com.practica.java_api_portfolio.infrastructure.persistence.entity;

import com.practica.java_api_portfolio.domain.model.AccountStatus;
import com.practica.java_api_portfolio.domain.model.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Table(name = "td_users")
@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    @OneToOne(mappedBy = "user")
    private EmailVerificationEntity emailVerification;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public void setEmailVerification(EmailVerificationEntity ev) {
        this.emailVerification = ev;
        if (ev != null) {
            ev.setUser(this);

        }
    }
}