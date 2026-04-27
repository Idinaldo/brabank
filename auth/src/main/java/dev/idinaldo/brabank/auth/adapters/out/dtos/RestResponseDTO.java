package dev.idinaldo.brabank.auth.adapters.out.dtos;

public class RestResponseDTO {
    private final String message;

    public RestResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
