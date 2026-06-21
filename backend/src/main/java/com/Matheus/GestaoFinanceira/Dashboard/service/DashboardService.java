package com.Matheus.GestaoFinanceira.Dashboard.service;

import com.Matheus.GestaoFinanceira.Transactions.entity.Expenses;
import com.Matheus.GestaoFinanceira.Transactions.entity.Income;
import com.Matheus.GestaoFinanceira.Transactions.entity.Transaction;
import com.Matheus.GestaoFinanceira.Transactions.repository.ExpensesRepository;
import com.Matheus.GestaoFinanceira.Transactions.repository.IncomesRepository;
import com.Matheus.GestaoFinanceira.Transactions.repository.TransactionRepository;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final ExpensesRepository expensesRepository;
    private final IncomesRepository incomesRepository;

    public DashboardService(ExpensesRepository expensesRepository, IncomesRepository incomesRepository) {
        this.expensesRepository = expensesRepository;
        this.incomesRepository = incomesRepository;
    }

    //Atualizar e deixar que o banco cuide disso depois
    public BigDecimal currentBalance(User user) {
        List<Income> allIncome = incomesRepository.getAllIncomesByUserId(user.getId());

        List<Expenses> allExpenses = expensesRepository.getAllExpensesByUserId(user.getId());

        BigDecimal totalIncomes = allIncome
                .stream()
                .map(Income::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = allExpenses
                .stream()
                .map(Expenses::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalIncomes.subtract(totalExpenses);
    }
}