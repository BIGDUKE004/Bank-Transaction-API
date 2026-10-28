package com.bank.transactionapi.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferRequest {
    private String destinationAccount;
    private String sourceAccount;
    private BigDecimal Amount;
}
