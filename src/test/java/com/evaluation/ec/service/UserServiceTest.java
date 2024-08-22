package com.evaluation.ec.service;

import com.evaluation.ec.common.Constants;
import com.evaluation.ec.common.GenericConverterUtils;
import com.evaluation.ec.common.JwtTokenUtil;
import com.evaluation.ec.dto.PhoneDto;
import com.evaluation.ec.dto.request.UserRequest;
import com.evaluation.ec.dto.response.UserResponse;
import com.evaluation.ec.exception.CustomErrorResponse;
import com.evaluation.ec.model.User;
import com.evaluation.ec.repository.UserRepository;
import com.evaluation.ec.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenericConverterUtils genericConverterUtils;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequest userRequest;
    private UUID userId;

    @Value("${custom.email.pattern}")
    private String emailRegex;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password123");

        userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("johndoe@dominio.cl");
        userRequest.setPassword("password123");
        userRequest.setPhones(Collections.singletonList(new PhoneDto()));
        emailRegex = "^[^@]+@dominio\\.cl$";
        ReflectionTestUtils.setField(userService, "emailRegex", emailRegex);
    }

    @Test
    void testCreateUser_Success() throws CustomErrorResponse {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(genericConverterUtils.convertToEntity(userRequest, User.class)).thenReturn(user);
        when(jwtTokenUtil.generateToken(user.getEmail())).thenReturn("jwt-token");

        String result = userService.createUser(userRequest);

        verify(userRepository, times(2)).saveAndFlush(user);
        assertEquals(Constants.MSG_USER_CREATED, result);
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        CustomErrorResponse exception = assertThrows(CustomErrorResponse.class, () -> userService.createUser(userRequest));

        assertEquals(Constants.EMAIL_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void testGetAllUsers_Success() {
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);
        when(genericConverterUtils.convertListToListDto(users, UserResponse.class))
                .thenReturn(Collections.singletonList(new UserResponse()));

        List<UserResponse> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Success() throws CustomErrorResponse {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(genericConverterUtils.convertToDto(user, UserResponse.class)).thenReturn(new UserResponse());

        UserResponse result = userService.getUserById(userId);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        CustomErrorResponse exception = assertThrows(CustomErrorResponse.class, () -> userService.getUserById(userId));

        assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
    }

    @Test
    void testUpdateUser_Success() throws CustomErrorResponse {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.saveAndFlush(user)).thenReturn(user);

        String result = userService.updateUser(userId, userRequest);

        assertEquals(Constants.MSG_USER_UPDATED, result);
        verify(userRepository, times(1)).saveAndFlush(user);
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        CustomErrorResponse exception = assertThrows(CustomErrorResponse.class, () -> userService.updateUser(userId, userRequest));

        assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
    }

}