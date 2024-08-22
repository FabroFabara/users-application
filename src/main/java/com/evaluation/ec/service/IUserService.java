package com.evaluation.ec.service;

import com.evaluation.ec.dto.request.UserRequest;
import com.evaluation.ec.dto.response.UserResponse;
import com.evaluation.ec.exception.CustomErrorResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    String createUser(UserRequest userRequest) throws CustomErrorResponse;

    List<UserResponse> getAllUsers() throws CustomErrorResponse;

    UserResponse getUserById(UUID id) throws CustomErrorResponse;

    String updateUser(UUID id, UserRequest userRequest) throws CustomErrorResponse;

    String deleteUser(UUID id) throws CustomErrorResponse;

}
