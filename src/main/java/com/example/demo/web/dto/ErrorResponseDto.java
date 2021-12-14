package com.example.demo.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private String message;
    private String cause;

    public ErrorResponseDto(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }
}
