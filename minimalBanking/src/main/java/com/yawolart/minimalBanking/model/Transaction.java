package com.yawolart.minimalBanking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Transaction {
    @Id
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "from_Acc")
    private Account fromAcc;

    @ManyToOne
    @JoinColumn(name = "toAcc")
    private Account toAcc;

    private BigDecimal amount;
    private LocalDate transactionAt;
}
