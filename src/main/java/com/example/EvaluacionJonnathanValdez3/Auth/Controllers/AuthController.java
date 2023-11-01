package com.example.EvaluacionJonnathanValdez3.Auth.Controllers;

import com.example.EvaluacionJonnathanValdez3.Auth.Services.AuthService;
import com.example.EvaluacionJonnathanValdez3.Auth.Dtos.AuthResponse;
import com.example.EvaluacionJonnathanValdez3.Auth.Dtos.LoginRequest;
import com.example.EvaluacionJonnathanValdez3.Auth.Dtos.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return  ResponseEntity.ok(authService.register(request));
    }
}
