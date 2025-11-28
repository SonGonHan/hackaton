package com.hackaton.task.user_auth.application.port.out;

import com.hackaton.task.user_auth.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public User save(User user);  // ← возвращай сохранённого пользователя

    public void delete(User user);

    public Optional<User> findByPhone(String phone);

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long id);

    public List<User> findAll();
}
