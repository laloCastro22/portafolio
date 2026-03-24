package com.practica.java_api_portfolio.infrastructure.config;

import com.practica.java_api_portfolio.application.policy.VerificationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PolicyBeans {
    final private VerificationCodeProperties props;

    public PolicyBeans(VerificationCodeProperties props) {
        this.props = props;
    }

    @Bean
    public VerificationPolicy crearPolitica(){
        return new VerificationPolicy(props.getTtl(), props.getMaxAttempts(), props.getResendCooldown());
    }
}
