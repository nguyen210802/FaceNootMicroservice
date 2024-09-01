package com.example.identity_service.repository.httpclient;

import com.example.identity_service.config.AuthenticationRequestInterceptor;
import com.example.identity_service.dto.ApiResponse;
import com.example.identity_service.dto.request.ProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
    configuration = AuthenticationRequestInterceptor.class)
public interface ProfileClient {
    @PostMapping(value = "/users/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProfileClient> createProfile(ProfileRequest request);
}
