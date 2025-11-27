package com.hackaton.task.user_auth.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;

    private String email;

    private String phone;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private UserRole role;
}
