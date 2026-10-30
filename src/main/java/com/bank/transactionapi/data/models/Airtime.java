package com.bank.transactionapi.data.models;

import com.bank.transactionapi.data.enums.NetworkProvider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airtime extends Transaction {
    @Enumerated(EnumType.STRING)
    private NetworkProvider networkProvider;
    private String phoneNumber;
}
