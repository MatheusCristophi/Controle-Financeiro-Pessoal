package com.Matheus.GestaoFinanceira.Exceptions.handler;

import com.Matheus.GestaoFinanceira.Exceptions.global.IdNotFoundException;
import com.Matheus.GestaoFinanceira.Exceptions.security.TokenCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ProblemDetail idNotFoundHandler(IdNotFoundException exception){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,"Id Não Encontrado"

                );

        problemDetail.setInstance(null);
        problemDetail.setType(java.net.URI.create("about:blank"));
        return problemDetail;
    }

    @ExceptionHandler(TokenCreateException.class)
    public ProblemDetail tokenCreateHandler(TokenCreateException exception){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,"Erro ao criar token"
                    
                );

        problemDetail.setInstance(null);
        problemDetail.setType(java.net.URI.create("about:blank"));
        return problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail badCredentialsHandler(BadCredentialsException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED, "Email ou senha inválidos."
                
        );

        problemDetail.setInstance(null);
        problemDetail.setType(java.net.URI.create("about:blank"));
        return problemDetail;
    }
}
