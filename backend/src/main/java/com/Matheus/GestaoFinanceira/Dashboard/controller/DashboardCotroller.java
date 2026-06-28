package com.Matheus.GestaoFinanceira.Dashboard.controller;

import com.Matheus.GestaoFinanceira.Dashboard.service.DashboardService;
import com.Matheus.GestaoFinanceira.User.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("dashboard/v1")
@PreAuthorize("hasRole('USER')")
public class DashboardCotroller {

    private final DashboardService service;

    public DashboardCotroller(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getCurrentBalance(@AuthenticationPrincipal User user){
        BigDecimal balance = service.currentBalance(user);
        return new ResponseEntity<>(balance,HttpStatus.OK);
    }

    @GetMapping("/expenses")
    public ResponseEntity<BigDecimal> getAllExpenses(@AuthenticationPrincipal User user){
        BigDecimal balanceExpenses = service.balanceExpenseForMonth(user);
        return new ResponseEntity<>(balanceExpenses, HttpStatus.OK);
    }

    @GetMapping("/incomes")
    public ResponseEntity<BigDecimal> getAllIncomes(@AuthenticationPrincipal User user){
        BigDecimal balanceIncomes = service.balanceIncomeForMonth(user);
        return new ResponseEntity<>(balanceIncomes, HttpStatus.OK);
    }

    @GetMapping("expenses/month")
    public ResponseEntity<BigDecimal> getAllExpensesOfPreviousMonth(@AuthenticationPrincipal User user){
        BigDecimal balanceExpenses = service.balanceExpenseForPreviousMonth(user);
        return new ResponseEntity<>(balanceExpenses, HttpStatus.OK);
    }

    @GetMapping("incomes/month")
    public ResponseEntity<BigDecimal> getAllIncomesOfPreviousMonth(@AuthenticationPrincipal User user){
        BigDecimal balanceIncomes = service.balanceIncomeForPreviousMonth(user);
        return new ResponseEntity<>(balanceIncomes, HttpStatus.OK);
    }
}
