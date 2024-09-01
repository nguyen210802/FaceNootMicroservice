package com.example.api_gateway.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse <T>{
    @Builder.Default
    int code = 1000;
    String message;
    T result;
}
