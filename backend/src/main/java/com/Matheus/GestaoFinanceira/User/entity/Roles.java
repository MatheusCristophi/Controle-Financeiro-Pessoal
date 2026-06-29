package com.Matheus.GestaoFinanceira.User.entity;

public enum Roles {
    USER("ROLE_USER");

    private String role;

    Roles(String role){
        this.role = role;
    }

    public String getRoles(){
        return role;
    }
}
