package com.Matheus.GestaoFinanceira.Transactions.service;

import com.Matheus.GestaoFinanceira.Exceptions.global.IdNotFoundException;
import com.Matheus.GestaoFinanceira.DTOs.transaction.TransactionRequest;
import com.Matheus.GestaoFinanceira.Transactions.entity.Expenses;
import com.Matheus.GestaoFinanceira.Transactions.entity.Income;
import com.Matheus.GestaoFinanceira.Transactions.entity.Transaction;
import com.Matheus.GestaoFinanceira.Transactions.repository.TransactionRepository;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    //Todas independente do tipo
    public List<Transaction> getAllTransactions(User user){
        return repository.findAllByUserId(user.getId());
    }

    //busca pelo id independente do tipo
    public Transaction getTransactionById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    public List<Transaction> getAllIncomes(UUID id){
        return repository.findAllWithTransactionTypeByUserId("INCOMES", id);
    }

    //retornar todas despesas
    public List<Transaction> getAllExpenses(UUID id){
        return repository.findAllWithTransactionTypeByUserId("EXPENSES", id);
    }

    public Income createIncomes(TransactionRequest request, User user) {
        Income income = new Income();


        income.setDescription(request.description());
        income.setCategory(request.category());
        income.setUser(user);
        income.setValue(request.value());
        income.setPaymentMethod(request.paymentMethods());
        income.setTransactionStatus(request.transactionStatus());
        income.setTransactionDate(request.transactionDate());

        return repository.save(income);
    }

    public Expenses createExpenses(TransactionRequest request, User user) {
        Expenses expenses = new Expenses();


        expenses.setDescription(request.description());
        expenses.setCategory(request.category());
        expenses.setUser(user);
        expenses.setValue(request.value());
        expenses.setPaymentMethod(request.paymentMethods());
        expenses.setTransactionStatus(request.transactionStatus());
        expenses.setTransactionDate(request.transactionDate());

        return repository.save(expenses);
    }

    public Transaction updateExpenses(UUID id, TransactionRequest request, User user) {
        Transaction transactionSaved = repository.findById(id)
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

        return repository.save(transactionSaved);
    }

    public void deleteTransaction(UUID id){
        repository.deleteById(id);
    }
}
