package com.example.demo.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveResponseDto {
    private Long id;

    public SaveResponseDto(Long id) {
        this.id = id;
    }
}
