package com.practica.java_api_portfolio.infrastructure.config;

import com.practica.java_api_portfolio.application.port.out.PasswordHasherPort;
import com.practica.java_api_portfolio.application.port.out.VerificationCodeGeneratorPort;
import com.practica.java_api_portfolio.application.port.out.VerificationCodeHasherPort;
import com.practica.java_api_portfolio.infrastructure.security.BcryptPasswordHasher;
import com.practica.java_api_portfolio.infrastructure.security.HmacVerificationCodeHasher;
import com.practica.java_api_portfolio.infrastructure.security.VerificationCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class SecurityBeans {
    final private SecurityCodeProperties security;
    final private SecurityEncoderProperties strength;

    public SecurityBeans(SecurityCodeProperties security, SecurityEncoderProperties strength) {
        this.security = security;
        this.strength = strength;
    }

    @Bean
    public byte[] secretBytes(){
        validateCode(this.security.getHmacSecret());
        try{
            return Base64.getDecoder().decode(this.security.getHmacSecret());
        } catch (Exception e) {
            throw new IllegalArgumentException("Secret invalido");
        }
    }
    /**
     * Crea y registra un bean de tipo {@link VerificationCodeHasherPort} en el contexto de Spring.
     *
     * <p>Este bean utiliza la implementación {@link HmacVerificationCodeHasher}, la cual aplica
     * el algoritmo HMAC (Hash-based Message Authentication Code) para generar y verificar
     * códigos de seguridad. El algoritmo requiere una clave secreta que se pasa como arreglo
     * de bytes.</p>
     *
     * @param secretBytes clave secreta en forma de arreglo de bytes, utilizada por el algoritmo HMAC
     * @return una instancia de {@link VerificationCodeHasherPort} basada en {@link HmacVerificationCodeHasher}
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * @Autowired
     * private VerificationCodeHasherPort hasher;
     *
     * String code = hasher.hash("123456");
     * boolean valido = hasher.verify("123456", code);
     * }
     * </pre>
     */
    @Bean
    public VerificationCodeHasherPort verificationCodeHasher(byte[] secretBytes) {
        return new HmacVerificationCodeHasher(secretBytes);
    }
    public void validateCode(String code) {
        if (code == null || code.isBlank() ){
            throw new IllegalArgumentException("El codigo pasado no puede estar vacio, verifique la informacion");
        }
        var regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El codigo no cumple con las especificaciones, intente con otro.");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(this.strength.getBcryptStrength());
    }
    @Bean
    public PasswordHasherPort passwordHasher(PasswordEncoder encoder) {
        return new BcryptPasswordHasher(encoder);
    }

    @Bean
    public VerificationCodeGeneratorPort verificationCodeGenerator(){
        return new VerificationCodeGenerator(new SecureRandom());
    }
}
