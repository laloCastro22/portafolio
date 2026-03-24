package com.practica.java_api_portfolio.infrastructure.email;

import com.practica.java_api_portfolio.application.port.out.EmailSenderPort;
import com.practica.java_api_portfolio.domain.model.Email;
import com.practica.java_api_portfolio.domain.model.VerificationCode;
import com.practica.java_api_portfolio.infrastructure.config.AppMailProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SmtpEmailSender implements EmailSenderPort {
    final private JavaMailSender sender;
    final private AppMailProperties props;
    @Override
    public void sendVerificationCode(Email to, VerificationCode code) {
       try {
           MimeMessage message = sender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
           helper.setFrom(props.getFrom());
           helper.setTo(to.value());
           helper.setText("Tu código es: " + code.value(), false);
           sender.send(message);
       } catch (MessagingException ex) {
           throw new IllegalStateException("Failed to send email", ex);
       }
    }
}
