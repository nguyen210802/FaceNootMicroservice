package com.example.profile_service.mapper;

import com.example.profile_service.dto.request.ProfileRequest;
import com.example.profile_service.dto.response.ProfileResponse;
import com.example.profile_service.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponse toProfileResponse(Profile profile);
    Profile toProfile(ProfileRequest request);
    void mappingProfile(@MappingTarget Profile profile, ProfileRequest request);
}
