package com.hackaton.task.user_auth.adapter.in.web.dto;

import com.hackaton.task.user_auth.domain.UserRole;
import lombok.Builder;

@Builder
public record UserDTO(String email,
                      String phone,
                      String password,
                      String firstName,
                      String lastName,
                      String middleName,
                      UserRole role
) {
}
