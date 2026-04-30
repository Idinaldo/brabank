package dev.idinaldo.brabank.auth.infrastructure.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.idinaldo.brabank.auth.adapters.out.dtos.RestResponseDTO;
import dev.idinaldo.brabank.auth.domain.exceptions.IdentityNotActiveException;
import dev.idinaldo.brabank.auth.domain.exceptions.IdentityNotFoundException;
import dev.idinaldo.brabank.auth.domain.exceptions.InvalidCredentialsException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<RestResponseDTO> invalidEmailExceptionHandler(InvalidEmailException exception) {
        return ResponseEntity.badRequest().body(new RestResponseDTO(exception.getLocalizedMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponseDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("Invalid request.");

        return ResponseEntity.badRequest().body(new RestResponseDTO(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestResponseDTO> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(new RestResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(IdentityNotFoundException.class)
    public ResponseEntity<RestResponseDTO> identityNotFoundExceptionHandler(IdentityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<RestResponseDTO> invalidCredentialsExceptionHandler(InvalidCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RestResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(IdentityNotActiveException.class)
    public ResponseEntity<RestResponseDTO> identityNotActiveExceptionHandler(IdentityNotActiveException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new RestResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestResponseDTO> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(new RestResponseDTO("Please verify your information and try again."));
    }
}