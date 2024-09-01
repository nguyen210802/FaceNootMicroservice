package com.example.profile_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "userId", unique = true)
    String userId;

    String username;
    @Column(unique = true)
    String email;

    String firstName;
    String lastName;

    LocalDate dob;
    String city;
}
