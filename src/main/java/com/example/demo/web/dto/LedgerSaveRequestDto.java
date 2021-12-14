package com.example.demo.web.dto;

import com.example.demo.model.ledger.Ledger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LedgerSaveRequestDto {
    private Long userId;
    private Integer price;
    private String title;
    private String memo;
    private LocalDateTime paidDate;

    @Builder
    public LedgerSaveRequestDto(Long userId, Integer price, String title, String memo, LocalDateTime paidDate) {
        this.userId = userId;
        this.price = price;
        this.title = title;
        this.memo = memo;
        this.paidDate = paidDate;
    }

    public Ledger toEntity() {
        return Ledger.builder()
                .userId(userId)
                .price(price)
                .title(title)
                .memo(memo)
                .isDeleted(Boolean.FALSE)
                .paidDate(paidDate)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
    }

}
