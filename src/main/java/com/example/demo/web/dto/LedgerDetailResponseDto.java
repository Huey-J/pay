package com.example.demo.web.dto;

import com.example.demo.model.ledger.Ledger;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LedgerDetailResponseDto {
    private Long id;
    private Integer price;
    private String title;
    private String memo;
    private LocalDateTime paidDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LedgerDetailResponseDto(Ledger entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.title = entity.getTitle();
        this.memo = entity.getMemo();
        this.paidDate = entity.getPaidDate();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
