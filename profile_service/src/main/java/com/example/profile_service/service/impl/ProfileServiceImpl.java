package com.example.profile_service.service.impl;

import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;
import com.example.profile_service.entity.Profile;
import com.example.profile_service.exception.AppException;
import com.example.profile_service.exception.ErrorCode;
import com.example.profile_service.mapper.ProfileMapper;
import com.example.profile_service.repository.ProfileRepository;
import com.example.profile_service.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceImpl implements ProfileService {
    ProfileRepository profileRepository;
    ProfileMapper mapper;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponse getProfile() {
        Profile profile = getMyProfile();
        return mapper.toProfileResponse(profile);
    }

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        Profile profile = profileMapper.toProfile(request);
        try {
            return profileMapper.toProfileResponse(profileRepository.save(profile));

        }catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.PROFILE_EXISTED);
        }
    }

    @Override
    public ProfileResponse update(ProfileRequest request) {
        Profile profile = getMyProfile();
        profileMapper.mappingProfile(profile, request);

        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    @Override
    public String delete() {
        profileRepository.delete(getMyProfile());

        return "delete successfully";
    }

    private Profile getMyProfile(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        Profile profile = profileRepository.findByUserId(userId);
        if(profile == null){
            throw new AppException(ErrorCode.PROFILE_NOT_EXIST);
        }

        return profile;
    }
}
