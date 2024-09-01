package com.example.identity_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @Size(min = 10, max = 20, message = "do dai phai lon hon 10 và nho hon 20")
    String username;
    @Size(min = 8, max = 20)
    String password;
    @Email
    String email;

    String firstName;
    String lastName;
    @Past
    LocalDate dob;
    String city;
}
