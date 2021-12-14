package com.example.demo.web.dto;

import com.example.demo.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;

    @Builder
    public UserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
