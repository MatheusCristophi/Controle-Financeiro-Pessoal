package com.Matheus.GestaoFinanceira.Transactions.service;

import com.Matheus.GestaoFinanceira.Exceptions.global.IdNotFoundException;
import com.Matheus.GestaoFinanceira.DTOs.transaction.TransactionRequest;
import com.Matheus.GestaoFinanceira.Transactions.entity.Expenses;
import com.Matheus.GestaoFinanceira.Transactions.entity.Income;
import com.Matheus.GestaoFinanceira.Transactions.entity.Transaction;
import com.Matheus.GestaoFinanceira.Transactions.repository.ExpensesRepository;
import com.Matheus.GestaoFinanceira.Transactions.repository.IncomesRepository;
import com.Matheus.GestaoFinanceira.Transactions.repository.TransactionRepository;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private TransactionRepository TRepository;
    private ExpensesRepository ERepository;
    private IncomesRepository IRepository;

    public TransactionService(TransactionRepository TRepository, ExpensesRepository ERepository, IncomesRepository IRepository) {
        this.TRepository = TRepository;
        this.ERepository = ERepository;
        this.IRepository = IRepository;
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions(User user){
        return TRepository.findAllByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    public Transaction getTransactionById(UUID id){
        return TRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Income> getAllIncomes(User user){
        return IRepository.getAllIncomesByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    public List<Expenses> getAllExpenses(User user){
        return ERepository.getAllExpensesByUserId(user.getId());
    }

    @Transactional
    public Income createIncomes(TransactionRequest request, User user) {
        Income income = new Income();


        income.setDescription(request.description());
        income.setCategory(request.category());
        income.setUser(user);
        income.setValue(request.value());
        income.setPaymentMethod(request.paymentMethods());
        income.setTransactionStatus(request.transactionStatus());
        income.setTransactionDate(request.transactionDate());

        return TRepository.save(income);
    }

    @Transactional
    public Expenses createExpenses(TransactionRequest request, User user) {
        Expenses expenses = new Expenses();


        expenses.setDescription(request.description());
        expenses.setCategory(request.category());
        expenses.setUser(user);
        expenses.setValue(request.value());
        expenses.setPaymentMethod(request.paymentMethods());
        expenses.setTransactionStatus(request.transactionStatus());
        expenses.setTransactionDate(request.transactionDate());

        return TRepository.save(expenses);
    }

    @Transactional
    public Transaction updateTransaction(UUID id, TransactionRequest request, User user) {
        Transaction transactionSaved = TRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        if (!request.description().isBlank()) {
            transactionSaved.setDescription(request.description());
        }
        if (!request.category().isBlank()) {
            transactionSaved.setCategory(request.category());
        }
        if (request.value() != null) {
            transactionSaved.setValue(request.value());
        }
        if (request.paymentMethods() != null) {
            transactionSaved.setPaymentMethod(request.paymentMethods());
        }
        transactionSaved.setTransactionStatus(request.transactionStatus());

        return TRepository.save(transactionSaved);
    }

    @Transactional
    public void deleteTransaction(UUID id){
        TRepository.deleteById(id);
    }
}
