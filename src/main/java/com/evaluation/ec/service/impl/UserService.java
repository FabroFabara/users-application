package com.evaluation.ec.service.impl;

import com.evaluation.ec.common.Constants;
import com.evaluation.ec.common.GenericConverterUtils;
import com.evaluation.ec.common.JwtTokenUtil;
import com.evaluation.ec.dto.PhoneDto;
import com.evaluation.ec.dto.request.UserRequest;
import com.evaluation.ec.dto.response.UserResponse;
import com.evaluation.ec.exception.CustomErrorResponse;
import com.evaluation.ec.model.Phone;
import com.evaluation.ec.model.User;
import com.evaluation.ec.repository.UserRepository;
import com.evaluation.ec.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.evaluation.ec.common.Constants.MSG_USER_CREATED;
import static com.evaluation.ec.common.Constants.MSG_USER_DELETED;
import static com.evaluation.ec.common.Constants.MSG_USER_UPDATED;
import static com.evaluation.ec.common.Constants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final GenericConverterUtils genericConverterUtils;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${custom.email.pattern}")
    private String emailRegex;



    @Override
    @Transactional
    public String createUser(UserRequest userRequest) throws CustomErrorResponse {
        validateEmail(userRequest.getEmail());
        User user = buildUserToSave(userRequest);
        user.setPhones(null);
        userRepository.saveAndFlush(user);

        assignUserToPhones(user, userRequest.getPhones());
        userRepository.saveAndFlush(user);
        return MSG_USER_CREATED;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return genericConverterUtils.convertListToListDto(users, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(UUID id) throws CustomErrorResponse {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomErrorResponse(USER_NOT_FOUND));
        return genericConverterUtils.convertToDto(user, UserResponse.class);
    }

    @Override
    public String updateUser(UUID id, UserRequest userRequest) throws CustomErrorResponse {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomErrorResponse(USER_NOT_FOUND));
        user.setModified(LocalDateTime.now());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        assignUserToPhones(user, userRequest.getPhones());
        userRepository.saveAndFlush(user);
        return MSG_USER_UPDATED;
    }

    @Override
    public String deleteUser(UUID id) throws CustomErrorResponse {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomErrorResponse(USER_NOT_FOUND));
        user.setActive(false);
        userRepository.saveAndFlush(user);
        return MSG_USER_DELETED;
    }

    private void validateEmail(String email) throws CustomErrorResponse {
        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            throw new CustomErrorResponse(Constants.EMAIL_ALREADY_EXISTS);
        }
        if (!email.matches(emailRegex)) {
            throw new CustomErrorResponse(Constants.INVALID_EMAIL);
        }
    }

    private User buildUserToSave(UserRequest userRequest) {
        User user = convertUserRequestToEntity(userRequest);
        user.setActive(true);
        setInitialDates(user);
        generateAndSetToken(user);
        return user;
    }

    private User convertUserRequestToEntity(UserRequest userRequest) {
        return genericConverterUtils.convertToEntity(userRequest, User.class);
    }

    private void setInitialDates(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
    }

    private void generateAndSetToken(User user) {
        String token = jwtTokenUtil.generateToken(user.getEmail());
        user.setToken(token);
    }

    private void assignUserToPhones(User user, List<PhoneDto> phones) {
        List<Phone> phonesToSave = genericConverterUtils.convertListDtoToListEntity(phones, Phone.class);
        if (phonesToSave != null) {
            for (Phone phone : phonesToSave) {
                phone.setUser(user);
            }
            user.setPhones(phonesToSave);
        }
    }
}
