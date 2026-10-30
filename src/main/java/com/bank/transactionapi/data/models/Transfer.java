package com.bank.transactionapi.data.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer extends Transaction{
    private String destinationAccount;
}
