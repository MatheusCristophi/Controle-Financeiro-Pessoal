package com.Matheus.GestaoFinanceira.DTOs.user;

import com.Matheus.GestaoFinanceira.Transactions.entity.Expenses;
import com.Matheus.GestaoFinanceira.Transactions.entity.Income;
import com.Matheus.GestaoFinanceira.User.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserResponse(String name,
                           String email,
                           List<Expenses> expenses,
                           List<Income> incomes) {

    public static UserResponse toUser(User user){
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getExpenses(),
                user.getIncomes()
        );
    }
}
