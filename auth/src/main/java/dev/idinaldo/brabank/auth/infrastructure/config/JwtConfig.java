package dev.idinaldo.brabank.auth.infrastructure.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {

    @NotBlank
    @Size(min = 32, message = "JWT secret must have at least 32 characters for HS256.")
    private String secret;

    @Min(value = 1, message = "JWT expiration must be greater than zero.")
    private long expirationSeconds;
}