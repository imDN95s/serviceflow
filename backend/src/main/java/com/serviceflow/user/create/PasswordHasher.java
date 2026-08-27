package com.serviceflow.user.create;

public interface PasswordHasher {

    String hash(String rawPassword);
}
