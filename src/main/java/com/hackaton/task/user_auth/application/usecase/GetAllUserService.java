package com.hackaton.task.user_auth.application.usecase;

import com.hackaton.task.user_auth.adapter.in.web.UserControllerMapper;
import com.hackaton.task.user_auth.adapter.in.web.dto.UserDTO;
import com.hackaton.task.user_auth.application.port.in.GetAllUsersUseCase;
import com.hackaton.task.user_auth.application.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUserService implements GetAllUsersUseCase {

    private final UserRepository repository;
    private final UserControllerMapper mapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

}
