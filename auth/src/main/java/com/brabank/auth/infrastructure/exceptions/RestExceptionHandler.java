package com.brabank.auth.infrastructure.exceptions;

import com.brabank.auth.adapters.out.dtos.RestResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    private ResponseEntity<RestResponseDTO> invalidEmailExceptionHandler(InvalidEmailException exception) {
        return ResponseEntity.badRequest().body(new RestResponseDTO(exception.getLocalizedMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<RestResponseDTO> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(new RestResponseDTO("Please verify your information and try again."));
    }
}
