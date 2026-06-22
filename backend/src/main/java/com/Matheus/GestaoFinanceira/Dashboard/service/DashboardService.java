package com.Matheus.GestaoFinanceira.Dashboard.service;

import com.Matheus.GestaoFinanceira.Transactions.entity.Expenses;
import com.Matheus.GestaoFinanceira.Transactions.entity.Income;
import com.Matheus.GestaoFinanceira.Transactions.repository.ExpensesRepository;
import com.Matheus.GestaoFinanceira.Transactions.repository.IncomesRepository;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    private final ExpensesRepository expensesRepository;
    private final IncomesRepository incomesRepository;

    public DashboardService(ExpensesRepository expensesRepository, IncomesRepository incomesRepository) {
        this.expensesRepository = expensesRepository;
        this.incomesRepository = incomesRepository;
    }

    //Deixar que banco de dados cuide dessa parte no futuro
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

    //Deixar que o banco de dados cuide dessa parte no futuro
    public BigDecimal balanceExpenseForMonth(User user) {
        List<Expenses> allExpense = expensesRepository.getAllExpensesByUserId(user.getId());

        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        return allExpense
                .stream()
                .filter(e -> e.getTransactionDate().isAfter(firstDayOfMonth))
                .filter(e -> e.getTransactionDate().isBefore(lastDayOfMonth))
                .map(Expenses::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal balanceIncomeForMonth(User user) {
        List<Income> allIncome = incomesRepository.getAllIncomesByUserId(user.getId());

        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        return allIncome
                .stream()
                .filter(i -> i.getTransactionDate().isBefore(lastDayOfMonth))
                .filter(i -> i.getTransactionDate().isAfter(firstDayOfMonth))
                .map(Income::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal balanceExpenseForPreviousMonth(User user) {
        List<Expenses> allExpenses = expensesRepository.getAllExpensesByUserId(user.getId());

        LocalDate now = LocalDate.now();
        LocalDate startPreviousMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endPreviousMonth = now.minusMonths(1)
                .withDayOfMonth(startPreviousMonth.lengthOfMonth());

        return allExpenses
                .stream()
                .filter(e -> e.getTransactionDate().isBefore(endPreviousMonth))
                .filter(e -> e.getTransactionDate().isAfter(startPreviousMonth))
                .map(Expenses::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal balanceIncomeForPreviousMonth(User user) {
        List<Income> allIncomes = incomesRepository.getAllIncomesByUserId(user.getId());

        LocalDate now = LocalDate.now();
        LocalDate startPreviousMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endPreviousMonth = now.minusMonths(1)
                .withDayOfMonth(startPreviousMonth.lengthOfMonth());

        return allIncomes
                .stream()
                .filter(i -> i.getTransactionDate().isBefore(endPreviousMonth))
                .filter(i -> i.getTransactionDate().isAfter(startPreviousMonth))
                .map(Income::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}