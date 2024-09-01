package com.example.identity_service.mapper;

import com.example.identity_service.dto.request.ProfileRequest;
import com.example.identity_service.dto.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileRequest toProfileRequest(UserRequest userRequest);
}
