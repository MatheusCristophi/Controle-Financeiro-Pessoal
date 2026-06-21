package com.Matheus.GestaoFinanceira.Transactions.repository;

import com.Matheus.GestaoFinanceira.Transactions.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, UUID> {

    List<Expenses> getAllExpensesByUserId(UUID id);
}
