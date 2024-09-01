package com.example.profile_service.controller;

import com.example.profile_service.dto.ApiResponse;
import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;
import com.example.profile_service.service.AdminProfileService;
import com.example.profile_service.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminProfileController {
    AdminProfileService adminProfileService;

    @GetMapping("/getAll")
    public ApiResponse<List<ProfileResponse>> getAll(){
        return ApiResponse.<List<ProfileResponse>>builder()
               .result(adminProfileService.getAll())
               .build();
    }

    @GetMapping("/getById/{id}")
    public ApiResponse<ProfileResponse> getById(@PathVariable String id){
        return ApiResponse.<ProfileResponse>builder()
                .result(adminProfileService.getProfileById(id))
                .build();
    }

    @GetMapping("/getByUserId/{userId}")
    public ApiResponse<ProfileResponse> getByUserId(@PathVariable String userId){
        return ApiResponse.<ProfileResponse>builder()
                .result(adminProfileService.getProfileByUserId(userId))
                .build();
    }

    @PutMapping("/updateById/{id}")
    public ApiResponse<ProfileResponse> updateById(@PathVariable String id, @RequestBody ProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .result(adminProfileService.updateProfileById(id, request))
                .build();
    }

    @PutMapping("/updateByUserId/{userId}")
    public ApiResponse<ProfileResponse> updateByUserIdId(@PathVariable String userId, @RequestBody ProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .result(adminProfileService.updateProfileByUserId(userId, request))
                .build();
    }

    @DeleteMapping("deleteById/{id}")
    public ApiResponse<String> deleteById(@PathVariable String id){
        return ApiResponse.<String>builder()
                .result(adminProfileService.deleteById(id))
                .build();
    }

    @DeleteMapping("deleteByUserId/{userId}")
    public ApiResponse<String> deleteByUserId(@PathVariable String userId){
        return ApiResponse.<String>builder()
                .result(adminProfileService.deleteByUserId(userId))
                .build();
    }
}
