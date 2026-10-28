package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.enums.NetworkProvider;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AirtimeRequest {
    private NetworkProvider networkProvider;
    private String sourceAccount;
    private BigDecimal amount;
    private String phoneNumber;
}
