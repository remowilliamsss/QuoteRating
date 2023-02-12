package ru.egorov.QuoteRating.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egorov.QuoteRating.model.User;
import ru.egorov.QuoteRating.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void create(User user) {
        user.setDateOfCreation(LocalDateTime.now());

        userRepository.save(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findByNameOrEmail(String name, String email) {
        return userRepository.findByNameOrEmail(name, email);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }
}
