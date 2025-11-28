package com.hackaton.task.user_auth.adapter.in.web;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.adapter.in.web.dto.SignInRequest;
import com.hackaton.task.user_auth.adapter.in.web.dto.AddUserRequest;
import com.hackaton.task.user_auth.application.port.in.AuthenticateUserUseCase;
import com.hackaton.task.user_auth.application.port.in.AddUserUseCase;
import com.hackaton.task.user_auth.application.port.in.command.AuthenticateUserCommand;
import com.hackaton.task.user_auth.application.port.in.command.AddUserCommand;
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

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(
            @RequestBody @Valid SignInRequest request) {
        AuthenticateUserCommand cmd = AuthenticateUserCommand.builder()
                .email(request.email())
                .password(request.password())
                .build();
        return authenticateUserUseCase.authenticateUser(cmd);
    }

}
