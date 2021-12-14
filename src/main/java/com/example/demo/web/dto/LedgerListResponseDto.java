package com.example.demo.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LedgerListResponseDto {
    private Integer length;
    private List<LedgerResponseDto> data;

    public LedgerListResponseDto(List<LedgerResponseDto> responseDtoList) {
        this.length = responseDtoList.size();
        this.data = responseDtoList;
    }
}
