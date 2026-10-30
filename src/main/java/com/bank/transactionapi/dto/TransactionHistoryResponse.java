package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.models.Transaction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionHistoryResponse {
    private List<Transaction> transactions;
}
