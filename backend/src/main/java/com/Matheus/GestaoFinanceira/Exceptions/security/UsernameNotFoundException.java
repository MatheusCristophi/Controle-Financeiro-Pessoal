package com.Matheus.GestaoFinanceira.Exceptions.security;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {
        super("Usuario não encontrado");
    }
}
