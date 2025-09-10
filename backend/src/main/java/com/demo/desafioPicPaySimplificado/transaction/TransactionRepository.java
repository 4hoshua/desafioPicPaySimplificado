package com.demo.desafioPicPaySimplificado.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

interface TransactionRepository extends JpaRepository <Transaction, Long> {}
