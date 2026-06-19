package com.Matheus.GestaoFinanceira.Transactions.Controller.DTOs;

import com.Matheus.GestaoFinanceira.Transactions.entity.PaymentMethods;
import com.Matheus.GestaoFinanceira.Transactions.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(String description,
                                  BigDecimal value,
                                  String category,
                                  PaymentMethods paymentMethods,
                                  LocalDate createdAt,
                                  LocalDate updateAt) {

    public static TransactionResponse toTransaction(Transaction transaction){
        return new TransactionResponse(
                transaction.getDescription(),
                transaction.getValue(),
                transaction.getCategory(),
                transaction.getPaymentMethod(),
                transaction.getCreatedAt(),
                transaction.getUpdateAt()
        );
    }
}
