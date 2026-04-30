package dev.idinaldo.brabank.auth.adapters.in.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.in.dtos.LoginRequest;
import dev.idinaldo.brabank.auth.adapters.in.dtos.LoginResponse;
import dev.idinaldo.brabank.auth.application.usecases.contracts.AuthenticateIdentityUseCase;
import dev.idinaldo.brabank.auth.application.usecases.contracts.RegisterUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping({"/api/auth", "/auth"})
public class AuthController {

    private final RegisterUseCase registerUseCase;
    private final AuthenticateIdentityUseCase authenticateIdentityUseCase;

    public AuthController(RegisterUseCase registerUseCase, AuthenticateIdentityUseCase authenticateIdentityUseCase) {
        this.registerUseCase = registerUseCase;
        this.authenticateIdentityUseCase = authenticateIdentityUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerIdentity(@RequestBody @Valid IdentityRequestDTO identityRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(identityRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticateIdentityUseCase.execute(loginRequest));
    }
}
