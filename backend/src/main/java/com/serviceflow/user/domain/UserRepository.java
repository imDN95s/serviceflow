package com.serviceflow.user.domain;

public interface UserRepository {

    boolean existsByEmail(String email);

    void save(User user);
}
