package com.example.demo.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Auto-Increament
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private LocalDateTime createdDate;

    @Builder
    public User(String email, String password, LocalDateTime createdDate) {
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
    }
}
