package com.Matheus.GestaoFinanceira.Transactions.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EXPENSES")
public class Expenses extends Transaction {
}
