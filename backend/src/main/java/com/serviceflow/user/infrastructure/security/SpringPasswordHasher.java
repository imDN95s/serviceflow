package com.serviceflow.user.infrastructure.security;

import com.serviceflow.user.create.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringPasswordHasher implements PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
