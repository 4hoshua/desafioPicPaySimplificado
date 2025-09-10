package com.demo.desafioPicPaySimplificado.wallet;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_wallet")
class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private WalletType type;

    public boolean common() {
        return WalletType.COMMON.equals(this.type);
    }

    public boolean merchant() {
        return WalletType.MERCHANT.equals(this.type);
    }

    public enum WalletType {
        COMMON, MERCHANT
    }

    public Wallet () {};

    public Wallet(WalletType type){
        this.balance = BigDecimal.ZERO;
        this.type = type;
    }

    public void addAmount(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void subtractAmount(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public WalletType getType() {
        return type;
    }
}
