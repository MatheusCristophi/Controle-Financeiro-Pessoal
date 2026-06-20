package com.Matheus.GestaoFinanceira.Transactions.Controller;

import com.Matheus.GestaoFinanceira.DTOs.transaction.TransactionRequest;
import com.Matheus.GestaoFinanceira.DTOs.transaction.TransactionResponse;
import com.Matheus.GestaoFinanceira.Transactions.service.TransactionService;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/transaction/v1")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponse>> showAllTransaction(@AuthenticationPrincipal User user){
        List<TransactionResponse> transactions = service.getAllTransactions(user)
                .stream()
                .map(TransactionResponse::toTransaction)
                .toList();

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/incomes/all")
    public ResponseEntity<List<TransactionResponse>> showAllIncomes(@AuthenticationPrincipal User user){
        List<TransactionResponse> transactions = service.getAllIncomes(user.getId())
                .stream()
                .map(TransactionResponse::toTransaction)
                .toList();

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/expenses/all")
    public ResponseEntity<List<TransactionResponse>> showAllExpenses(@AuthenticationPrincipal User user){
        List<TransactionResponse> transactions = service.getAllExpenses(user.getId())
                .stream()
                .map(TransactionResponse::toTransaction)
                .toList();

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransactionResponse> showTransactionById(@PathVariable UUID id){
        TransactionResponse tr = TransactionResponse.toTransaction(service.getTransactionById(id));
        return ResponseEntity.ok(tr);
    }

    @PostMapping("/incomes/create")
    public ResponseEntity<TransactionResponse> createIncome(@RequestBody TransactionRequest request,
                                                                 @AuthenticationPrincipal User user){
        var response = TransactionResponse.toTransaction(service.createIncomes(request, user));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/expenses/create")
    public ResponseEntity<TransactionResponse> createExpense(@RequestBody TransactionRequest request,
                                                             @AuthenticationPrincipal User user){
        var response = TransactionResponse.toTransaction(service.createExpenses(request, user));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@RequestBody TransactionRequest request,
                                                                 @PathVariable UUID id,
                                                                 @AuthenticationPrincipal User user
                                                                 ){
        var response = TransactionResponse.toTransaction(service.updateExpenses(id, request, user));

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id){
        service.deleteTransaction(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
