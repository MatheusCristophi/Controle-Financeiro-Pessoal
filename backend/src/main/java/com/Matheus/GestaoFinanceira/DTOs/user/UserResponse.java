package com.Matheus.GestaoFinanceira.DTOs.user;

import com.Matheus.GestaoFinanceira.User.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserResponse(@NotBlank
                           String name,
                           @Email
                           String email) {

    public static UserResponse toUser(User user){
        return new UserResponse(
                user.getName(),
                user.getEmail()
        );
    }
}
