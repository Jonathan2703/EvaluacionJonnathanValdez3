package com.example.EvaluacionJonnathanValdez3.Auth.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Username is required")
    String username;
    @NotEmpty(message = "Password is required")
    String password;
    @NotEmpty(message = "First name is required")
    String firstName;
    @NotEmpty(message = "Last name is required")
    String lastName;
    @NotEmpty(message = "Country is required")
    String country;
}
