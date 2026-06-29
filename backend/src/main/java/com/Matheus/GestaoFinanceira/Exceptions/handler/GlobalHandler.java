package com.Matheus.GestaoFinanceira.Exceptions.handler;

import com.Matheus.GestaoFinanceira.Exceptions.global.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ProblemDetail idNotFoundHandler(IdNotFoundException exception){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND,"Id Não Encontrado");

        problemDetail.setInstance(null);
        problemDetail.setType(java.net.URI.create("about:blank"));
        return problemDetail;
    }
}
