package com.example.identity_service.controller;

import com.example.identity_service.dto.ApiResponse;
import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    AdminService adminService;

    @GetMapping("/getAll")
    public ApiResponse<List<UserResponse>> getAll(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(adminService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id){
        return ApiResponse.<UserResponse>builder()
                .result(adminService.getUserById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(adminService.updateUser(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id){
        return ApiResponse.<String>builder()
                .result(adminService.deleteUser(id))
                .build();
    }
}
