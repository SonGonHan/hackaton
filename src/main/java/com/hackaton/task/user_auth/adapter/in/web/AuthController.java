package com.hackaton.task.user_auth.adapter.in.web;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.adapter.in.web.dto.SignInRequest;
import com.hackaton.task.user_auth.adapter.in.web.dto.SignUpRequest;
import com.hackaton.task.user_auth.application.port.in.AuthenticateUserUseCase;
import com.hackaton.task.user_auth.application.port.in.RegisterUserUseCase;
import com.hackaton.task.user_auth.application.port.in.command.AuthenticateUserCommand;
import com.hackaton.task.user_auth.application.port.in.command.RegisterUserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(
            @RequestBody @Valid SignUpRequest request) {
        RegisterUserCommand cmd = RegisterUserCommand.builder()
                .email(request.email())
                .phone(request.phone())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .middleName(request.middleName())
                .rawPassword(request.password())
                .build();
        return registerUserUseCase.registerUser(cmd);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(
            @RequestBody @Valid SignInRequest request) {
        AuthenticateUserCommand cmd = AuthenticateUserCommand.builder()
                .phone(request.phone())
                .password(request.password())
                .build();
        return authenticateUserUseCase.authenticateUser(cmd);
    }
}
