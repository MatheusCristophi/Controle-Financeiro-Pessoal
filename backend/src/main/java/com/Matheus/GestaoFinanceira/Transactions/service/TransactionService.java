package com.Matheus.GestaoFinanceira.Transactions.service;

import com.Matheus.GestaoFinanceira.Exceptions.User.IdNotFoundException;
import com.Matheus.GestaoFinanceira.Transactions.Controller.DTOs.TransactionRequest;
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

    public List<Transaction> getAllTransactions(User user){
        return repository.findByUserId(user.getId());
    }

    public Transaction getTransactionById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    public Transaction createTransaction(TransactionRequest request, User user) {
        Transaction transaction = new Transaction();


        transaction.setDescription(request.description());
        transaction.setCategory(request.category());
        transaction.setUser(user);
        transaction.setValue(request.value());
        transaction.setPaymentMethod(request.paymentMethods());
        transaction.setCreatedAt(LocalDate.now());
        transaction.setUpdateAt(null);

        return repository.save(transaction);
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
