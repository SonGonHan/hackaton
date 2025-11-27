package com.hackaton.task.user_auth.application.port.in.command;

import lombok.Builder;

@Builder
public record RegisterUserCommand(String email,
                                  String phone,
                                  String rawPassword,
                                  String firstName,
                                  String lastName,
                                  String middleName) {
}
