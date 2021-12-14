package com.example.demo.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String email;
    private String token;

    public UserResponseDto(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
