package com.demo.desafioPicPaySimplificado.wallet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
class WalletController {

    private final WalletManagement management;

    WalletController(WalletManagement management) {
        this.management = management;
    }

    @PostMapping("/wallets")
    ResponseEntity<WalletDTO> addWallet(@RequestBody AddWalletRequest request){
        var WalletDTO = management.addWallet(request.type());
        return ResponseEntity.ok(WalletDTO);
    }

    @GetMapping("/wallets/{id}")
    ResponseEntity<WalletDTO> findById(@PathVariable("id") Long id){
        return management.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/wallets/{id}/setBalance")
    ResponseEntity<WalletDTO> addAmount(@PathVariable("id") Long id, @RequestBody UpdateBalanceRequest request) {
        var walletDTO = management.addAmount(id, request.amount());
        return ResponseEntity.ok(walletDTO);
    }

    record AddWalletRequest(Wallet.WalletType type){}
    record UpdateBalanceRequest(BigDecimal amount) {}
}
