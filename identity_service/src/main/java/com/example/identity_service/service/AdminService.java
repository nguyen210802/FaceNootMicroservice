package com.example.identity_service.service;

import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAll();
    UserResponse getUserById(String id);
    UserResponse updateUser(String id, UserRequest request);
    String deleteUser(String id);
}
