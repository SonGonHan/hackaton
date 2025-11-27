package com.hackaton.task.user_auth.application.port.in;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.application.port.in.command.RegisterUserCommand;

public interface RegisterUserUseCase {
    JwtAuthenticationResponse registerUser(RegisterUserCommand command);
}
