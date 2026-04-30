package dev.idinaldo.brabank.auth.adapters.in.dtos;

public record LoginResponse(String accessToken, String tokenType, long expiresIn) {
}