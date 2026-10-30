package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.enums.TransactionType;
import com.bank.transactionapi.data.models.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferResponse {
    private long id;
    private TransactionType type;
    private int discountPercentage;
    private LocalDateTime createdAt;
    private String destinationAccount;
    private String sourceAccount;
    private BigDecimal amount;

}
