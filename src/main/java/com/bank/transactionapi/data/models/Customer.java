package com.bank.transactionapi.data.models;

import com.bank.transactionapi.data.enums.CustomerType;
import com.bank.transactionapi.data.enums.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    private LocalDate joinedDate;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts = new ArrayList<>();
}