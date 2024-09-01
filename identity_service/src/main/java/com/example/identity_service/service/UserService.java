package com.example.identity_service.service;

import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;

public interface UserService {
    UserResponse getMyInfo();
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    String deleteUser();
}
