package com.yawolart.minimalBanking.model;

import com.yawolart.minimalBanking.enumaration.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private LocalDateTime createdAt;
}
