package com.evaluation.ec.controller;

import com.evaluation.ec.dto.request.UserRequest;
import com.evaluation.ec.dto.response.UserResponse;
import com.evaluation.ec.exception.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Tag(name = "User", description = "User operations")
@RequestMapping("/user")
public interface IUserController {

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) throws CustomErrorResponse;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "204", description = "No users found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUsers() throws CustomErrorResponse;

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<UserResponse> getUserById(UUID id) throws CustomErrorResponse;

    @Operation(summary = "Update user by id")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<String> updateUser(UUID id, @RequestBody UserRequest userRequest) throws CustomErrorResponse;

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<String> deleteUser(UUID id) throws CustomErrorResponse;
}
