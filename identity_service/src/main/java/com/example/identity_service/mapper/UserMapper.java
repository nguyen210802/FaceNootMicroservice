package com.example.identity_service.mapper;

import com.example.identity_service.dto.request.UserRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);
    User mappingUser(@MappingTarget User user, UserRequest request);
}
