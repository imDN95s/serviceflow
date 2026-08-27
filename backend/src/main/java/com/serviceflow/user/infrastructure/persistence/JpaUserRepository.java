package com.serviceflow.user.infrastructure.persistence;

import com.serviceflow.user.domain.User;
import com.serviceflow.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    @Override
    public boolean existsByEmail(String email) {
        return springDataUserRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        springDataUserRepository.save(user);
    }

}
