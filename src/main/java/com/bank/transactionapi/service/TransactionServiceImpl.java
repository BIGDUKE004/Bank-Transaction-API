package com.bank.transactionapi.service;

import com.bank.transactionapi.data.models.Account;
import com.bank.transactionapi.data.models.Customer;
import com.bank.transactionapi.data.repository.AccountRepository;
import com.bank.transactionapi.data.repository.TransactionRepository;
import com.bank.transactionapi.discount.DiscountRuleImpl;
import com.bank.transactionapi.dto.AirtimeRequest;
import com.bank.transactionapi.dto.AirtimeResponse;
import com.bank.transactionapi.dto.TransferRequest;
import com.bank.transactionapi.dto.TransferResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

public class TransactionServiceImpl implements TransactionService {
    private AccountRepository accountRepository;
    private DiscountRuleImpl discountRule;
    private TransactionRepository transactionRepository;
    private Account account;

    @Override
    public TransferResponse transfer(TransferRequest request) {
        BigDecimal discount = null;
        LocalDateTime start = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime end = YearMonth.now().atEndOfMonth().atTime(LocalTime.MAX);
        long count = transactionRepository.countByAccountCustomerAndCreatedAtBetween(account.getCustomer(), start, end);
        Customer customer = accountRepository.findByAccountNumber(request.getSourceAccount());
        boolean overFourYears = customer.getJoinedDate().plusYears(4).isBefore(LocalDate.now());
        if(overFourYears == true){
            discount.add(discountRule.discount(overFourYears, count, request.getAmount()));
        } else {
            discount.add(discountRule.discount(customer, request.getAmount(), count));
        }
        return null;
    }

    @Override
    public AirtimeResponse airtime(AirtimeRequest request) {
        return null;
    }
}
