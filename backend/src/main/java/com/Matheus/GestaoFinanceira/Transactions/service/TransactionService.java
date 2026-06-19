package com.Matheus.GestaoFinanceira.Transactions.service;

import com.Matheus.GestaoFinanceira.Exceptions.User.IdNotFoundException;
import com.Matheus.GestaoFinanceira.Transactions.Controller.DTOs.TransactionRequest;
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

    public List<Transaction> getAllIncomes(){
        return repository.findAllWithTransactionType("INCOMES");
    }

    //retornar todas despesas
    public List<Transaction> getAllExpenses(){
        return repository.findAllWithTransactionType("EXPENSES");
    }

    public Income createIncomes(TransactionRequest request, User user) {
        Income income = new Income();


        income.setDescription(request.description());
        income.setCategory(request.category());
        income.setUser(user);
        income.setValue(request.value());
        income.setPaymentMethod(request.paymentMethods());
        income.setCreatedAt(LocalDate.now());
        income.setUpdateAt(null);

        return repository.save(income);
    }

    public Expenses createExpenses(TransactionRequest request, User user) {
        Expenses expenses = new Expenses();


        expenses.setDescription(request.description());
        expenses.setCategory(request.category());
        expenses.setUser(user);
        expenses.setValue(request.value());
        expenses.setPaymentMethod(request.paymentMethods());
        expenses.setCreatedAt(LocalDate.now());
        expenses.setUpdateAt(null);

        return repository.save(expenses);
    }

    public Transaction updateTransaction(UUID id, TransactionRequest request, User user) {
        Transaction transactionSaved = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        transactionSaved.setUser(user);
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
        transactionSaved.setUpdateAt(LocalDate.now());

        return repository.save(transactionSaved);
    }

    public void deleteTransaction(UUID id){
        repository.deleteById(id);
    }
}
