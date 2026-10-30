package com.bank.transactionapi.controller;

import com.bank.transactionapi.dto.*;
import com.bank.transactionapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController    {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/api/v1/transactions/transfer")
    public TransferResponse transfer(@Valid @RequestBody TransferRequest request){
        return this.transactionService.transfer(request);
    }

    @PostMapping("/api/v1/transactions/airtime")
    public AirtimeResponse airtime(@Valid @RequestBody AirtimeRequest request){
        return this.transactionService.airtime(request);
    }

    @GetMapping("/api/v1/accounts/{accountNumber}/transactions")
    public TransactionHistoryResponse transactionHistory(@PathVariable TransactionHistoryRequest request){
        return this.transactionService.transactionHistory(request);
    }
}

