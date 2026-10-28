package com.bank.transactionapi.controller;

import com.bank.transactionapi.dto.TransferRequest;
import com.bank.transactionapi.dto.TransferResponse;
import com.bank.transactionapi.service.TransactionService;
import com.bank.transactionapi.service.TransactionServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
