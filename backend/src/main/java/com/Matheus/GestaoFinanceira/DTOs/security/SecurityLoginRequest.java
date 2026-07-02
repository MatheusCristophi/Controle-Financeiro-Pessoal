package com.Matheus.GestaoFinanceira.DTOs.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SecurityLoginRequest ( @NotBlank(message = "O Email não pode estar vazio")
                                     @Email
                                     String email, 
                                     @Size(min = 8, max = 20, message = "A senha não pode ter menos de 8 caracteres")
                                     String password) {
    
}
