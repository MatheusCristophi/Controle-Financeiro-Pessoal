package com.Matheus.GestaoFinanceira.User.controller.DTOs;

import com.Matheus.GestaoFinanceira.User.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserResponse(@NotBlank
                           String name,
                           @Email
                           String email) {

    public static User toUser(UserResponse response){
        return new User(
                response.name,
                response.email
        );
    }
}
