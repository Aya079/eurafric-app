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
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@eurafric.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Admin créé : admin / admin123");
        }

        if (userRepository.findByUsername("ayoub").isEmpty()) {
            User user = new User();
            user.setUsername("ayoub");
            user.setEmail("ayoub@eurafric.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole("ROLE_USER");
            userRepository.save(user);
            System.out.println("✅ Utilisateur créé : ayoub / 123456");
        }
    }
}
