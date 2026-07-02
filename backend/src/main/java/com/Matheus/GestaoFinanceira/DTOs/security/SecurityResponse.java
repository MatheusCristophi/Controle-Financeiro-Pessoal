package com.Matheus.GestaoFinanceira.DTOs.security;

public record SecurityResponse (String email,
                                String token
) {
    public static SecurityResponse toResponse(String email, String token) {
    return new SecurityResponse(
            email,
            token
    );
}
}
