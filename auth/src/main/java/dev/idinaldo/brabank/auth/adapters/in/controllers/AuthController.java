package dev.idinaldo.brabank.auth.adapters.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<?> registerIdentity() {
        return ResponseEntity.ok("Endpoint is up!");
    }
}
