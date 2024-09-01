package com.example.identity_service.controller;

import com.example.identity_service.dto.ApiResponse;
import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/my_info")
    public ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<UserResponse> updateUser(UserRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request))
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteUser(){
        return ApiResponse.<String>builder()
               .result(userService.deleteUser())
               .build();
    }
}
