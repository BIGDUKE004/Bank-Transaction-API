package com.bank.transactionapi.data.models;

import com.bank.transactionapi.data.enums.NetworkProvider;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private NetworkProvider networkProvider;
    private String phoneNumber;
}
