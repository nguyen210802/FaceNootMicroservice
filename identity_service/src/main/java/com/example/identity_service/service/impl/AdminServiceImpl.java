package com.example.identity_service.service.impl;

import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.User;
import com.example.identity_service.mapper.UserMapper;
import com.example.identity_service.repository.UserRepository;
import com.example.identity_service.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminServiceImpl implements AdminService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("User not found")
                )
        );
    }

    @Override
    public UserResponse updateUser(String id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        userMapper.mappingUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Delete successfully";
    }
}
