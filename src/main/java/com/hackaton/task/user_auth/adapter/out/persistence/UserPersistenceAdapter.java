package com.hackaton.task.user_auth.adapter.out.persistence;

import com.hackaton.task.user_auth.application.port.out.UserRepository;
import com.hackaton.task.user_auth.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        var entity = mapper.toEntity(user);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void delete(User user) {
        var entity = mapper.toEntity(user);
        repository.delete(entity);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return repository.findByPhone(phone).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
