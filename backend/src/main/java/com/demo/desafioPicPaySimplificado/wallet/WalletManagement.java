package com.demo.desafioPicPaySimplificado.wallet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class WalletManagement {

    private final WalletRepository repository;
    private final WalletMapper mapper;

    public WalletManagement(WalletRepository repository, WalletMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Services para endpoints
    public WalletDTO addWallet (Wallet.WalletType type) {
        var wallet = new Wallet(type);
        return mapper.toDTO(repository.save(wallet));
    }

    public WalletDTO addAmount (Long id, BigDecimal amount) {
        var wallet = repository.getReferenceById(id);
        wallet.addAmount(amount);
        return mapper.toDTO(repository.save(wallet));
    }

    @Transactional(readOnly = true)
    public Optional<WalletDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    //Logic para Transaction
    public void subtractAmount (Long id, BigDecimal amount) {
        var wallet = repository.getReferenceById(id);
        wallet.subtractAmount(amount);
        repository.save(wallet);
    }

    public Boolean isMerchant(Long id){
        var wallet = repository.getReferenceById(id);
        return wallet.merchant();
    }


    public BigDecimal getAmount(Long id) {
        var wallet = repository.getReferenceById(id);
        return wallet.getBalance();
    }
}
