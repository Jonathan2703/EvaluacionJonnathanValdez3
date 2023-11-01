package com.example.EvaluacionJonnathanValdez3.Auth.Services;

import com.example.EvaluacionJonnathanValdez3.Auth.Dtos.AuthResponse;
import com.example.EvaluacionJonnathanValdez3.Auth.Dtos.LoginRequest;
import com.example.EvaluacionJonnathanValdez3.Auth.Dtos.RegisterRequest;
import com.example.EvaluacionJonnathanValdez3.Jwt.JwtService;
import com.example.EvaluacionJonnathanValdez3.User.Entities.Enums.Role;
import com.example.EvaluacionJonnathanValdez3.User.Entities.User;
import com.example.EvaluacionJonnathanValdez3.User.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user= userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
