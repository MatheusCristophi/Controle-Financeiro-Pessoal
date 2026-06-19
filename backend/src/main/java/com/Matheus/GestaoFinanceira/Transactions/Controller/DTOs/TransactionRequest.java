package com.Matheus.GestaoFinanceira.Transactions.Controller.DTOs;

import com.Matheus.GestaoFinanceira.Transactions.entity.PaymentMethods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequest(@NotBlank(message = "Description can't be blank")
                                 String description,
                                 @NotNull(message = "Value can't be null")
                                 BigDecimal value,
                                 @NotBlank(message = "Category can't be blank")
                                 String category,
                                 @NotEmpty(message = "PaymentMethod can't be empty")
                                 PaymentMethods paymentMethods
                                 ){
}
