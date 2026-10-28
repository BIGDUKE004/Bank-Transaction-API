package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferResponse {
    private int id;
    private TransactionType type;
    private String discountPercentage;
    private BigDecimal amountCharged;
    private LocalDateTime createdAt;
    private String destinationAccount;
    private String sourceAccount;
    private BigDecimal amount;
}
