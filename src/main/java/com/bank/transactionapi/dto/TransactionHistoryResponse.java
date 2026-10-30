package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.models.Transaction;
import lombok.Data;

import java.util.ArrayList;
@Data
public class TransactionHistoryResponse {
    private ArrayList<Transaction> transactions;
}
