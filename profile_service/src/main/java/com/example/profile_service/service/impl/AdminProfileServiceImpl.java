package com.example.profile_service.service.impl;

import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;
import com.example.profile_service.entity.Profile;
import com.example.profile_service.exception.AppException;
import com.example.profile_service.exception.ErrorCode;
import com.example.profile_service.mapper.ProfileMapper;
import com.example.profile_service.repository.ProfileRepository;
import com.example.profile_service.service.AdminProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminProfileServiceImpl implements AdminProfileService {
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;

    @Override
    public List<ProfileResponse> getAll() {
        return profileRepository.findAll().stream()
                .map(profileMapper::toProfileResponse)
                .toList();
    }

    @Override
    public ProfileResponse getProfileById(String id) {
        return profileMapper.toProfileResponse(
                profileRepository.findById(id).orElseThrow(
                        () -> new AppException(ErrorCode.PROFILE_NOT_EXIST)
                )
        );
    }

    @Override
    public ProfileResponse getProfileByUserId(String userId) {
        Profile profile = profileRepository.findByUserId(userId);
        if(profile == null){
            throw new AppException(ErrorCode.PROFILE_NOT_EXIST);
        }
        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public ProfileResponse updateProfileById(String id, ProfileRequest request) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.PROFILE_NOT_EXIST));
        profileMapper.mappingProfile(profile, request);

        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    @Override
    public ProfileResponse updateProfileByUserId(String userId, ProfileRequest request) {
        Profile profile = profileRepository.findByUserId(userId);
        if(profile == null){
            throw new AppException(ErrorCode.PROFILE_NOT_EXIST);
        }

        profileMapper.mappingProfile(profile, request);
        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    @Override
    public String deleteById(String id) {
        profileRepository.deleteById(id);
        return "Delete Successfully";
    }

    @Override
    public String deleteByUserId(String userId) {
        Profile profile = profileRepository.findByUserId(userId);
        if(profile == null){
            throw new AppException(ErrorCode.PROFILE_NOT_EXIST);
        }
        profileRepository.delete(profile);
        return "Delete successfully";
    }
}
