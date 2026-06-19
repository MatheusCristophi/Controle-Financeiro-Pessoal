package com.Matheus.GestaoFinanceira.Transactions.repository;

import com.Matheus.GestaoFinanceira.Transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
