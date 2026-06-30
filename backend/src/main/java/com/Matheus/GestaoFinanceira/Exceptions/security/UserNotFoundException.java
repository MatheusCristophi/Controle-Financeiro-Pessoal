package com.Matheus.GestaoFinanceira.Exceptions.security;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuario não encontrado");
    }
}
