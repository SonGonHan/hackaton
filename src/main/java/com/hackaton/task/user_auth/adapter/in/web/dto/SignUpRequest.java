package com.hackaton.task.user_auth.adapter.in.web.dto;

public record SignUpRequest (String email,
                             String phone,
                             String password,
                             String firstName,
                             String lastName,
                             String middleName) {
}
