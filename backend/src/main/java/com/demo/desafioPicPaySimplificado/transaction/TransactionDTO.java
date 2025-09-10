package com.demo.desafioPicPaySimplificado.transaction;

import java.math.BigDecimal;

public record TransactionDTO (Long id, BigDecimal amount, Transaction.Wallet payer, Transaction.Wallet payee){
}
