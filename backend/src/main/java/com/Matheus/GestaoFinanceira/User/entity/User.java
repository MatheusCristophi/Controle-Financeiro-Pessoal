package com.Matheus.GestaoFinanceira.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private BigDecimal balance;

    @OneToMany(mappedBy = "")
    private Object despesas;

    @OneToMany(mappedBy = "")
    private Object receita;

    @Column
    private String password;

    @Column(name = "user_role", length = 25)
    private Roles role;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
