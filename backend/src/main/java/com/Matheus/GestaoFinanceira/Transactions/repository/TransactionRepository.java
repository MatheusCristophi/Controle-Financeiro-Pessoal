package com.Matheus.GestaoFinanceira.Transactions.repository;

import com.Matheus.GestaoFinanceira.Transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByUserId(UUID id);

    List<Transaction> findAllWithTransactionTypeByUserId(String type, UUID id);
}
