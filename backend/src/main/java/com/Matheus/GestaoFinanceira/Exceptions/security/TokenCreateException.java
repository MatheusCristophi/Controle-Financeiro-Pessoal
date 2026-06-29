package com.Matheus.GestaoFinanceira.Exceptions.security;

public class TokenCreateException extends RuntimeException {
    public TokenCreateException() {
        super("Erro Ao criar o Token");
    }
}
