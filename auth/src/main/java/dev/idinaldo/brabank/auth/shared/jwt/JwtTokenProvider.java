package dev.idinaldo.brabank.auth.shared.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import dev.idinaldo.brabank.auth.application.ports.out.GeneratedToken;
import dev.idinaldo.brabank.auth.application.ports.out.TokenProvider;
import dev.idinaldo.brabank.auth.domain.models.Identity;
import dev.idinaldo.brabank.auth.infrastructure.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final JwtConfig jwtConfig;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public GeneratedToken generateToken(Identity identity) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plusSeconds(jwtConfig.getExpirationSeconds());

        String accessToken = Jwts.builder()
                .subject(identity.getId().toString())
                .claim("email", identity.getEmail())
                .claim("role", identity.getRole().name())
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiresAt))
                .signWith(resolveSecretKey())
                .compact();

        return new GeneratedToken(accessToken, jwtConfig.getExpirationSeconds());
    }

    public Claims parseClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(resolveSecretKey())
                .build()
                .parseSignedClaims(token);

        return claimsJws.getPayload();
    }

    private SecretKey resolveSecretKey() {
        byte[] secretBytes = jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}