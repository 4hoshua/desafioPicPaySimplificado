package com.demo.desafioPicPaySimplificado.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
public class TransactionController {

    private final TransactionManagement management;


    public TransactionController(TransactionManagement management) {
        this.management = management;
    }

    @PostMapping("/transaction")
    ResponseEntity<TransactionDTO> makeTransaction(@RequestBody makeTransactionRequest request) {
        var TransactionDTO = management.makeTransaction(request.amount(), request.payer(), request.payee());
        return ResponseEntity.ok(TransactionDTO);
    }


    record makeTransactionRequest(BigDecimal amount, Transaction.Wallet payer, Transaction.Wallet payee){}
}
