package com.demo.desafioPicPaySimplificado.wallet;

import java.math.BigDecimal;

public record WalletDTO (Long id, BigDecimal balance, Wallet.WalletType type){
}
