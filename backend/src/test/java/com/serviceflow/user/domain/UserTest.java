package com.serviceflow.user.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void shouldCreateActiveUser() {
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");

        User user = User.create(
                id,
                "technican@srvflow.com",
                "hashed-pass",
                UserRole.TECHNICIAN
        );

        assertEquals(id, user.getId());
        assertEquals("technican@srvflow.com", user.getEmail());
        assertEquals("hashed-pass", user.getPasswordHash());
        assertEquals(UserRole.TECHNICIAN, user.getRole());
        assertEquals(UserStatus.ACTIVE, user.getStatus());
    }
}
