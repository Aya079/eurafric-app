package com.eurafric.entreprise.controller;

import com.eurafric.entreprise.entity.User;
import com.eurafric.entreprise.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Récupérer tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ✅ Créer un nouvel utilisateur
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Encode le mot de passe avant sauvegarde
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Définit un rôle par défaut si non fourni
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }

        return userRepository.save(user);
    }
}
