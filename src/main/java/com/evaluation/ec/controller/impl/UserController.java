package com.evaluation.ec.controller.impl;

import com.evaluation.ec.controller.IUserController;
import com.evaluation.ec.dto.request.UserRequest;
import com.evaluation.ec.dto.response.UserResponse;
import com.evaluation.ec.exception.CustomErrorResponse;
import com.evaluation.ec.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;

    @Override
    public ResponseEntity<String> createUser(UserRequest userRequest) throws CustomErrorResponse {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() throws CustomErrorResponse {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(UUID id) throws CustomErrorResponse {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<String> updateUser(UUID id, UserRequest userRequest) throws CustomErrorResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRequest));
    }

    @Override
    public ResponseEntity<String> deleteUser(UUID id) throws CustomErrorResponse {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }
}
