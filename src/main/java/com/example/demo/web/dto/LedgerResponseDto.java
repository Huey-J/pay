package com.example.demo.web.dto;

import com.example.demo.model.ledger.Ledger;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LedgerResponseDto {
    private Long id;
    private Integer price;
    private String title;
    private LocalDateTime paidDate;
    private Boolean isDeleted;

    public LedgerResponseDto(Ledger entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.title = entity.getTitle();
        this.paidDate = entity.getPaidDate();
        this.isDeleted = entity.getIsDeleted();
    }
}
