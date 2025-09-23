package com.demo.desafioPicPaySimplificado.transaction;

import com.demo.desafioPicPaySimplificado.client.AuthorizationService;
import com.demo.desafioPicPaySimplificado.client.NotificationService;
import com.demo.desafioPicPaySimplificado.wallet.WalletManagement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class TransactionManagement {

    private final WalletManagement wallet;
    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;


    public TransactionManagement(WalletManagement wallet, TransactionRepository repository, TransactionMapper mapper, AuthorizationService authorizationService, NotificationService notificationService) {
        this.wallet = wallet;
        this.repository = repository;
        this.mapper = mapper;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional
    public TransactionDTO makeTransaction(BigDecimal amount, Transaction.Wallet Payer, Transaction.Wallet Payee) {

        validateTransaction(amount, Payer, Payee);
        return executeTransaction(amount, Payer, Payee);

    }

    private void validateTransaction(BigDecimal amount, Transaction.Wallet Payer, Transaction.Wallet Payee){

        if (wallet.isMerchant(Payer.id())) {throw new RuntimeException("Forbidden");}

        if (wallet.getAmount(Payee.id()).compareTo(amount) < 0){throw new RuntimeException("Forbidden");}

        if (!authorizationService.isAuthorized()) {throw new RuntimeException("Forbidden");}
    }

    private TransactionDTO executeTransaction(BigDecimal amount, Transaction.Wallet Payer, Transaction.Wallet Payee){
        wallet.subtractAmount(Payer.id(), amount);
        wallet.addAmount(Payee.id(), amount);

        var Transaction = new Transaction(amount, Payer, Payee);

        CompletableFuture.runAsync(notificationService::sendNotification);

        return mapper.toDTO(repository.save(Transaction));
    }
}
