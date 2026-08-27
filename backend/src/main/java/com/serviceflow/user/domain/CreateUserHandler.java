package com.serviceflow.user.domain;

import com.serviceflow.user.create.CreateUserCommand;
import com.serviceflow.user.create.PasswordHasher;
import com.serviceflow.user.create.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserHandler {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Transactional
    public void handle(CreateUserCommand command) {

        String email = normalizeEmail(command.email());

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }

        String passwordHash = passwordHasher.hash(command.temporaryPassword());

        User user = User.create(
                UUID.randomUUID(),
                email,
                passwordHash,
                command.role()
        );

        userRepository.save(user);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

}
