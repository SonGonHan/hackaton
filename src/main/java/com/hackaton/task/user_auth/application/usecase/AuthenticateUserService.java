package com.hackaton.task.user_auth.application.usecase;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.application.port.in.AuthenticateUserUseCase;
import com.hackaton.task.user_auth.application.port.in.command.AuthenticateUserCommand;
import com.hackaton.task.user_auth.application.port.out.TokenGeneratorPort;
import com.hackaton.task.user_auth.application.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserService implements AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;

    @Override
    public JwtAuthenticationResponse authenticateUser(AuthenticateUserCommand command) {
        var user = userRepository.findByPhone(command.phone())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!passwordEncoder.matches(command.password(), user.getPassword())){
            throw new BadCredentialsException("Wrong credentials");
        }

        String token = tokenGenerator.generateAccessToken(user);
        return new JwtAuthenticationResponse(token);
    }
}
