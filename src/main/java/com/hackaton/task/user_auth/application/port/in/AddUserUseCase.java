package com.hackaton.task.user_auth.application.port.in;

import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.application.port.in.command.AddUserCommand;

public interface AddUserUseCase {
    JwtAuthenticationResponse addUser(AddUserCommand command);
}
