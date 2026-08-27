package com.serviceflow.user.create;

import com.serviceflow.user.domain.CreateUserHandler;
import com.serviceflow.user.domain.User;
import com.serviceflow.user.domain.UserRepository;
import com.serviceflow.user.domain.UserRole;
import com.serviceflow.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CreateUserHandlerTest {

    @Test
    void shouldCreateUser() {
        FakeUserRepository repository = new FakeUserRepository();
        FakePasswordHasher passwordHasher = new FakePasswordHasher();

        CreateUserHandler handler =
                new CreateUserHandler(repository, passwordHasher);

        handler.handle(new CreateUserCommand(
                " Technician@ServiceFlow.com ",
                "temporary-password",
                UserRole.TECHNICIAN
        ));

        User savedUser = repository.savedUser;

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals("technician@serviceflow.com", savedUser.getEmail());
        assertEquals("hashed-temporary-password", savedUser.getPasswordHash());
        assertEquals(UserRole.TECHNICIAN, savedUser.getRole());
        assertEquals(UserStatus.ACTIVE, savedUser.getStatus());
    }

    @Test
    void shouldRejectExistingEmail() {
        FakeUserRepository repository = new FakeUserRepository();
        repository.emailExists = true;

        FakePasswordHasher passwordHasher = new FakePasswordHasher();

        CreateUserHandler handler =
                new CreateUserHandler(repository, passwordHasher);

        CreateUserCommand command = new CreateUserCommand(
                "existing@serviceflow.com",
                "temporary-password",
                UserRole.MANAGER
        );

        assertThrows(
                UserAlreadyExistsException.class,
                () -> handler.handle(command)
        );
    }

    private static class FakeUserRepository implements UserRepository {

        private boolean emailExists;
        private User savedUser;

        @Override
        public boolean existsByEmail(String email) {
            return emailExists;
        }

        @Override
        public void save(User user) {
            this.savedUser = user;
        }
    }

    private static class FakePasswordHasher implements PasswordHasher {

        @Override
        public String hash(String rawPassword) {
            return "hashed-" + rawPassword;
        }
    }
}
