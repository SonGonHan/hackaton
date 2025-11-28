package com.hackaton.task.user_auth.application.usecase;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.application.port.in.AddUserUseCase;
import com.hackaton.task.user_auth.application.port.in.command.AddUserCommand;
import com.hackaton.task.user_auth.application.port.out.TokenGeneratorPort;
import com.hackaton.task.user_auth.application.port.out.UserRepository;
import com.hackaton.task.user_auth.domain.User;
import com.hackaton.task.user_auth.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddUserService implements AddUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;


    @Override
    public JwtAuthenticationResponse addUser(AddUserCommand command) {
        if (userRepository.findByEmail(command.email()).isPresent()){
            throw new IllegalArgumentException("Phone already exists");
        }
        var user = User.builder()
                .phone(command.phone())
                .email(command.email())
                .password(passwordEncoder.encode(command.rawPassword()))
                .firstName(command.firstName())
                .lastName(command.lastName())
                .middleName(command.middleName())
                .role(UserRole.EMPLOYEE)
                .build();
        user = userRepository.save(user);
        String token = tokenGenerator.generateAccessToken(user);
        return   new JwtAuthenticationResponse(token);

    }
}
