package com.hackaton.task.user_auth.application.port.in;

import com.hackaton.task.user_auth.adapter.in.web.dto.UserDTO;

import java.util.List;

public interface GetAllUsersUseCase {
    List<UserDTO> getAllUsers();
}
