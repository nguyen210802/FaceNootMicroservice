package com.example.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequest {
    String userId;

    String username;
    String email;

    String firstName;
    String lastName;

    LocalDate dob;
    String city;
}
