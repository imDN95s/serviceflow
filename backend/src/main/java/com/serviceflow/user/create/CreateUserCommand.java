package com.serviceflow.user.create;

import com.serviceflow.user.domain.UserRole;

public record CreateUserCommand(
        String email,
        String temporaryPassword,
        UserRole role
) {
}
