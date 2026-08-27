package com.serviceflow.user.infrastructure.persistence;

import com.serviceflow.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
}
