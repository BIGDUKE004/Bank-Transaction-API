package com.bank.transactionapi.service;

import com.bank.transactionapi.dto.AirtimeRequest;
import com.bank.transactionapi.dto.AirtimeResponse;
import com.bank.transactionapi.dto.TransferRequest;
import com.bank.transactionapi.dto.TransferResponse;
import com.bank.transactionapi.dto.TransactionHistoryResponse;
import com.bank.transactionapi.dto.TransactionHistoryRequest;

public interface TransactionService {
    TransferResponse transfer(TransferRequest request);
    AirtimeResponse  airtime(AirtimeRequest request);
    TransactionHistoryResponse transactionHistory(TransactionHistoryRequest request);
}
