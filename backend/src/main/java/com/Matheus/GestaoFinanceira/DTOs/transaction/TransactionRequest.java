package com.Matheus.GestaoFinanceira.DTOs.transaction;

import com.Matheus.GestaoFinanceira.Transactions.entity.PaymentMethods;
import com.Matheus.GestaoFinanceira.Transactions.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(@NotBlank(message = "Description can't be blank")
                                 String description,
                                 @NotNull(message = "Value can't be null")
                                 BigDecimal value,
                                 @NotBlank(message = "Category can't be blank")
                                 String category,
                                 @NotNull
                                 Status transactionStatus,
                                 @NotNull
                                 LocalDate transactionDate,
                                 @NotNull(message = "PaymentMethod can't be empty")
                                 PaymentMethods paymentMethods
                                 ){
}
