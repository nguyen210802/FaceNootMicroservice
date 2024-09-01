package com.example.profile_service.controller;

import com.example.profile_service.dto.ApiResponse;
import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;
import com.example.profile_service.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    ProfileService profileService;

    @GetMapping("/my_info")
    public ApiResponse<ProfileResponse> getMyInfo(){
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getProfile())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<ProfileResponse> createProfile(@RequestBody ProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.createProfile(request))
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProfileResponse> updateProfile(@RequestBody ProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.update(request))
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteProfile(){
        return ApiResponse.<String>builder()
               .result(profileService.delete())
               .build();
    }
}
