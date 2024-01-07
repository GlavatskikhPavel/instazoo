package ru.instazoo.backend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.instazoo.backend.entities.User;
import ru.instazoo.backend.entities.enums.Role;
import ru.instazoo.backend.payload.request.SingUpRequest;
import ru.instazoo.backend.repositories.UserRepository;
import ru.instazoo.backend.services.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(SingUpRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        user.setActive(true);

        try {
            log.info("Saving User {}", request.getEmail());
            return userRepository.save(user);
        } catch (Exception exception) {
            log.error("Error during registration {}", exception.getMessage());
            return null;
        }
    }
}
