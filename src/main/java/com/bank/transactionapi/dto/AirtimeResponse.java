package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.enums.NetworkProvider;
import com.bank.transactionapi.data.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class AirtimeResponse {
    private long id;
    private String phoneNumber;
    private BigDecimal discountAmount;
    private TransactionType type;
    private NetworkProvider networkProvider;
    private LocalDateTime createdAt;
    private String sourceAccount;
    private BigDecimal amount;
}
