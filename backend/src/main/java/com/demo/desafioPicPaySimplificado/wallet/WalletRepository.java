package com.demo.desafioPicPaySimplificado.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

interface WalletRepository extends JpaRepository <Wallet, Long> {}
