package com.Matheus.GestaoFinanceira.DTOs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank(message = "nome não pode ser vazio")
                          String name,
                          @NotBlank(message = "email não pode ser vazio")
                          @Email(message = "email inválido")
                          String email,
                          @NotBlank(message = "senha não pode ser vazia")
                          String password) {
}
