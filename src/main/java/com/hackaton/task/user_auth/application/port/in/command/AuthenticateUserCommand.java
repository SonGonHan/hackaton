package com.hackaton.task.user_auth.application.port.in.command;

import lombok.Builder;

@Builder
public record AuthenticateUserCommand (String email,
                                       String password){
}
