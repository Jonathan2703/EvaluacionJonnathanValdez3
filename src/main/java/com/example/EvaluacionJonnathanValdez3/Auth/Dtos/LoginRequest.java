package com.example.EvaluacionJonnathanValdez3.Auth.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "Username is required")
    String username;
    @NotEmpty(message = "Password is required")
    String password;
}
