package com.bank.transactionapi.controller;

import com.bank.transactionapi.dto.*;
import com.bank.transactionapi.service.TransactionService;
import com.bank.transactionapi.service.TransactionServiceImpl;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RestController
public class TransactionController    {

    private final TransactionService transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transer")
    public TransferResponse transfer(@RequestBody TransferRequest request){
        return this.transactionService.transfer(request);
    }

    @PostMapping("/airtime")
    public AirtimeResponse airtime(@RequestBody AirtimeRequest request){
        return this.transactionService.airtime(request);
    }

    @GetMapping("/{accountNumber}")
    public TransactionHistoryResponse transactionHistory(@PathVariable TransactionHistoryRequest request){
        return this.transactionService.transactionHistory(request);
    }
}

