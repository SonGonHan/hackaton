package com.hackaton.task.user_auth.application.usecase;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.application.port.in.RegisterUserUseCase;
import com.hackaton.task.user_auth.application.port.in.command.RegisterUserCommand;
import com.hackaton.task.user_auth.application.port.out.TokenGeneratorPort;
import com.hackaton.task.user_auth.application.port.out.UserRepository;
import com.hackaton.task.user_auth.domain.User;
import com.hackaton.task.user_auth.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;


    @Override
    public JwtAuthenticationResponse registerUser(RegisterUserCommand command) {
        if (userRepository.findByPhone(command.phone()).isPresent()){
            throw new IllegalArgumentException("Phone already exists");
        }
        var user = User.builder()
                .phone(command.phone())
                .email(command.email())
                .password(passwordEncoder.encode(command.rawPassword()))
                .firstName(command.firstName())
                .lastName(command.lastName())
                .middleName(command.middleName())
                .role(UserRole.CLIENT)
                .build();
        user = userRepository.save(user);
        String token = tokenGenerator.generateAccessToken(user);
        return   new JwtAuthenticationResponse(token);

    }
}
