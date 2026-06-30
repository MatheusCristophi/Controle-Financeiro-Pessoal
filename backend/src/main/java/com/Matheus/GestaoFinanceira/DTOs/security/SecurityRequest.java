package com.Matheus.GestaoFinanceira.DTOs.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SecurityRequest ( @NotBlank(message = "O nome de Usuário não pode estar vazio")
                                String name, 
                                @NotBlank(message = "O Email não pode estar vazio")
                                String email, 
                                @Size(min = 8, max = 20, message = "A senha não pode ter menos de 8 caracteres")
                                String password) {
    
}
