package com.example.demo.model.ledger;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Auto-Increament
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column
    private Integer price;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column
    private LocalDateTime paidDate;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime modifiedDate;

    @Builder
    public Ledger(Long userId, Integer price, String title, String memo, Boolean isDeleted, LocalDateTime paidDate, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.userId = userId;
        this.price = price;
        this.title = title;
        this.memo = memo;
        this.isDeleted = isDeleted;
        this.paidDate = paidDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
