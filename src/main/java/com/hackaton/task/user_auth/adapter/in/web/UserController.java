package com.hackaton.task.user_auth.adapter.in.web;

import com.hackaton.task.user_auth.adapter.in.web.dto.AddUserRequest;
import com.hackaton.task.user_auth.adapter.in.web.dto.JwtAuthenticationResponse;
import com.hackaton.task.user_auth.adapter.in.web.dto.UserDTO;
import com.hackaton.task.user_auth.application.port.in.AddUserUseCase;
import com.hackaton.task.user_auth.application.port.in.GetAllUsersUseCase;
import com.hackaton.task.user_auth.application.port.in.command.AddUserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AddUserUseCase addUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;

    @PostMapping("/add-user")
    public JwtAuthenticationResponse addUser(
            @RequestBody @Valid AddUserRequest request) {
        AddUserCommand cmd = AddUserCommand.builder()
                .email(request.email())
                .phone(request.phone())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .middleName(request.middleName())
                .rawPassword(request.password())
                .build();
        return addUserUseCase.addUser(cmd);
    }

    @GetMapping("/all-users")
    public List<UserDTO> allUsers() {
        return getAllUsersUseCase.getAllUsers();
    }
}
