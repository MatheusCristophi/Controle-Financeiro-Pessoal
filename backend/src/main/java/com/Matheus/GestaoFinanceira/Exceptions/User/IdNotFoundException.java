package com.Matheus.GestaoFinanceira.Exceptions.User;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(UUID id) {
        super("Id: "+id+" not found");
    }
}
