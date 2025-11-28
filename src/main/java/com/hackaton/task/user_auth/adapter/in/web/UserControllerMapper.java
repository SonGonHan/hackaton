package com.hackaton.task.user_auth.adapter.in.web;

import com.hackaton.task.user_auth.adapter.in.web.dto.UserDTO;
import org.springframework.stereotype.Component;
import com.hackaton.task.user_auth.domain.User;

@Component
public class UserControllerMapper {
    
    public UserDTO toDto(User domain) {
        return UserDTO.builder()
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .password(domain.getPassword())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .middleName(domain.getMiddleName())
                .role(domain.getRole())
                .build();
    }

    public User toDomain(UserDTO dto) {
        return User.builder()
                .email(dto.email())
                .phone(dto.phone())
                .password(dto.password())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .middleName(dto.middleName())
                .role(dto.role())
                .build();
    }
}
