package com.hackaton.task.user_auth.application.port.out;

import com.hackaton.task.user_auth.domain.User;


public interface TokenGeneratorPort {
    String generateAccessToken(User user);
    boolean isTokenValid(String token);
    Long extractUserId(String token);
}
