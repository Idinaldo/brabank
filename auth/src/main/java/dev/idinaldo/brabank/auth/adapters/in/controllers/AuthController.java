package dev.idinaldo.brabank.auth.adapters.in.controllers;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<?> registerIdentity(@RequestBody @Valid IdentityRequestDTO identityRequestDTO) {
        return ResponseEntity.ok("Endpoint is up!");
    }
}
