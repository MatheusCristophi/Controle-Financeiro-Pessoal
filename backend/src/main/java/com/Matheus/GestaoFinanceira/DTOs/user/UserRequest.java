package com.Matheus.GestaoFinanceira.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank(message = "name can't be blank")
                          String name,
                          @Email(message = "invalid email")
                          String email,
                          @NotBlank(message = "password can't be blank")
                          String password) {
}
