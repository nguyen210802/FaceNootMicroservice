package com.example.identity_service.service.impl;

import com.example.event.dto.NotificationEvent;
import com.example.identity_service.dto.request.ProfileRequest;
import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.User;
import com.example.identity_service.enums.RoleEnum;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.ProfileMapper;
import com.example.identity_service.mapper.UserMapper;
import com.example.identity_service.repository.UserRepository;
import com.example.identity_service.repository.httpclient.ProfileClient;
import com.example.identity_service.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    UserRepository userRepository;
    UserMapper userMapper;
    ProfileMapper profileMapper;
    ProfileClient profileClient;
    PasswordEncoder passwordEncoder;
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public UserResponse getMyInfo() {
        User user = getMyUser();
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(RoleEnum.USER.toString());
        try {
            user = userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        ProfileRequest profileRequest = profileMapper.toProfileRequest(request);
        profileRequest.setUserId(user.getId());

//        var profile = profileClient.createProfile(profileRequest);
        log.info("Profile: {}", profileRequest);

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .channel("EMAIL")
                .recipient(request.getEmail())
                .subject("Welcome to bookteria")
                .body("Hello, " + request.getUsername())
                .build();

        // Publish message to kafka
        kafkaTemplate.send("notification-delivery", notificationEvent);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        User user = getMyUser();

        userMapper.mappingUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public String deleteUser() {
        User user = getMyUser();
        userRepository.delete(user);

        return "Delete successfully";
    }

    private User getMyUser(){
        var context = SecurityContextHolder.getContext();
        String id = context.getAuthentication().getName();
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
    }
}
