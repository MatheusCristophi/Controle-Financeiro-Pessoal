package com.Matheus.GestaoFinanceira.Transactions.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INCOMES")
public class Income extends Transaction {
}
