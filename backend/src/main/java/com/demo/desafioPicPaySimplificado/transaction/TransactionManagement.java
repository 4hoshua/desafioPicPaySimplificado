package com.demo.desafioPicPaySimplificado.transaction;

import com.demo.desafioPicPaySimplificado.wallet.WalletManagement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@Transactional
public class TransactionManagement {

    private final WalletManagement wallet;
    private final TransactionRepository repository;
    private final TransactionMapper mapper;


    public TransactionManagement(WalletManagement wallet, TransactionRepository repository, TransactionMapper mapper) {
        this.wallet = wallet;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public TransactionDTO makeTransaction(BigDecimal amount, Transaction.Wallet Payer, Transaction.Wallet Payee) {

        //todo refatorar esses ifs
        if (wallet.isMerchant(Payer.id())) {
            throw new RuntimeException("Forbidden");
        }

        if (wallet.getAmount(Payee.id()).compareTo(amount) < 0){
            throw new RuntimeException("Forbidden");
        }

        wallet.subtractAmount(Payer.id(), amount);
        wallet.addAmount(Payee.id(), amount);

        var Transaction = new Transaction(amount, Payer, Payee);

        return mapper.toDTO(repository.save(Transaction));
    }
}
