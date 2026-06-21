package com.Matheus.GestaoFinanceira.Transactions.repository;

import com.Matheus.GestaoFinanceira.Transactions.entity.Income;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IncomesRepository extends JpaRepository<Income, UUID> {
    List<Income> getAllIncomesByUserId(UUID id);
}
