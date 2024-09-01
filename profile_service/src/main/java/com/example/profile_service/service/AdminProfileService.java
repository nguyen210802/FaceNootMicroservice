package com.example.profile_service.service;

import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;

import java.util.List;

public interface AdminProfileService {
    List<ProfileResponse> getAll();
    ProfileResponse getProfileById(String id);
    ProfileResponse getProfileByUserId(String userId);
    ProfileResponse updateProfileById(String id, ProfileRequest request);
    ProfileResponse updateProfileByUserId(String userId, ProfileRequest request);
    String deleteById(String id);
    String deleteByUserId(String userId);
}
