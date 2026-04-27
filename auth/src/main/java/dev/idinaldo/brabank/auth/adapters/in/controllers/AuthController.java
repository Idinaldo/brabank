package dev.idinaldo.brabank.auth.adapters.in.controllers;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.application.usecases.contracts.RegisterUseCase;
import dev.idinaldo.brabank.auth.domain.services.IdentityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUseCase registerUseCase;

    public AuthController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerIdentity(@RequestBody @Valid IdentityRequestDTO identityRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(identityRequestDTO));
    }
}
