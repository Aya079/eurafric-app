package com.eurafric.entreprise.config;

import com.eurafric.entreprise.entity.User;
import com.eurafric.entreprise.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initUsers() {
        createOrUpdateUser("admin", "admin@eurafric.com", "admin123", "ROLE_ADMIN");
        createOrUpdateUser("ayoub", "ayoub@eurafric.com", "123456", "ROLE_USER");
        createOrUpdateUser("Aya", "aya@eurafric.com", "aya123", "ROLE_USER"); // respecter la casse
    }

    private void createOrUpdateUser(String username, String email, String password, String role) {
        userRepository.findByUsername(username).ifPresentOrElse(
                existingUser -> {
                    // Optionnel : mettre à jour le mot de passe si nécessaire
                    existingUser.setPassword(passwordEncoder.encode(password));
                    userRepository.save(existingUser);
                    System.out.println("🔹 Mot de passe mis à jour pour : " + username);
                },
                () -> {
                    User newUser = new User();
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setPassword(passwordEncoder.encode(password));
                    newUser.setRole(role);
                    userRepository.save(newUser);
                    System.out.println("✅ Utilisateur créé : " + username + " / " + password);
                }
        );
    }
}
