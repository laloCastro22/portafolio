package com.practica.java_api_portfolio.infrastructure.persistence.repository;

import com.practica.java_api_portfolio.infrastructure.persistence.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailVerificationEntityRepository extends JpaRepository<EmailVerificationEntity, UUID> {
}
