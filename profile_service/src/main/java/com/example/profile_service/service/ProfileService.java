package com.example.profile_service.service;

import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse getProfile();
    ProfileResponse createProfile(ProfileRequest request);
    ProfileResponse update(ProfileRequest request);
    String delete();
}
