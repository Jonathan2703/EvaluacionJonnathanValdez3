package com.example.EvaluacionJonnathanValdez3.User.Repositories;

import com.example.EvaluacionJonnathanValdez3.User.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
