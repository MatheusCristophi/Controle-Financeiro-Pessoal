package com.Matheus.GestaoFinanceira.Transactions.Controller;

import com.Matheus.GestaoFinanceira.Transactions.Controller.DTOs.TransactionResponse;
import com.Matheus.GestaoFinanceira.Transactions.service.TransactionService;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/transaction/v1")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionResponse>> getAllTransaction(@AuthenticationPrincipal User user){
        List<TransactionResponse> transactions = service.getAllTransactions(user)
                .stream()
                .map(TransactionResponse::toTransaction)
                .toList();

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(UUID id){
        TransactionResponse tr = TransactionResponse.toTransaction(service.getTransactionById(id));
        return ResponseEntity.ok(tr);
    }
}
