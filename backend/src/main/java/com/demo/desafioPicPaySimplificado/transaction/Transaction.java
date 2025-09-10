package com.demo.desafioPicPaySimplificado.transaction;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_transaction")
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "wallet_payer_id"))
    private Wallet Payer;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "wallet_payee_id"))
    private Wallet Payee;

    public record Wallet(Long id) {}

    public Transaction(){}

    public Transaction(BigDecimal amount, Wallet payer, Wallet payee) {
        this.amount = amount;
        Payer = payer;
        Payee = payee;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Wallet getPayer() {
        return Payer;
    }

    public Wallet getPayee() {
        return Payee;
    }
}
