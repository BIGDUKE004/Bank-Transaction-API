package com.bank.transactionapi.service;

import com.bank.transactionapi.data.enums.TransactionType;
import com.bank.transactionapi.data.models.*;
import com.bank.transactionapi.data.repository.AccountRepository;
import com.bank.transactionapi.data.repository.TransactionRepository;
import com.bank.transactionapi.dto.*;
import com.bank.transactionapi.exception.AccountNotFoundException;
import com.bank.transactionapi.exception.InvalidAmountException;
import com.bank.transactionapi.util.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private AccountRepository accountRepository;
    private com.bank.transactionapi.discount.DiscountRuleImpl discountRule;
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, com.bank.transactionapi.discount.DiscountRuleImpl discountRule) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.discountRule = discountRule;
    }

    @Override
    public TransferResponse transfer(TransferRequest request) {
        Account account =  accountRepository.findByAccountNumber(request.getSourceAccount());
        if(account == null){
            throw new AccountNotFoundException("Account not found");
        }
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        BigDecimal discount = BigDecimal.valueOf(0);
        LocalDateTime start = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime end = YearMonth.now().atEndOfMonth().atTime(LocalTime.MAX);
        Customer customer = account.getCustomer();
        long count = transactionRepository.countByAccountCustomerAndCreatedAtBetween(customer, start, end);

        boolean overFourYears = account.getCustomer()
                .getJoinedDate()
                .isBefore(LocalDate.now().minusYears(4));

        BigDecimal byType = discountRule.discount(customer, request.getAmount(), count);
        BigDecimal byLoyalty = discountRule.discount(overFourYears, count, request.getAmount());
        discount = byType.max(byLoyalty);

        Transfer transaction = new Transfer();
        transaction.setAccount(account);
        transaction.setDestinationAccount(request.getDestinationAccount());
        transaction.setAmount(request.getAmount());
        transaction.setDiscountAmount(discount);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setType(TransactionType.TRANSFER);

        Transaction saved = this.transactionRepository.save(transaction);

        TransferResponse transferResponse = Mapper.mapToTransferResponse(saved, request.getDestinationAccount());

        return transferResponse;
    }

    @Override
    public AirtimeResponse airtime(AirtimeRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getSourceAccount());
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        Airtime airtime = new Airtime();
        airtime.setAmount(request.getAmount());
        airtime.setPhoneNumber(request.getPhoneNumber());
        airtime.setAccount(account);
        airtime.setDiscountAmount(BigDecimal.ZERO);
        airtime.setType(TransactionType.AIRTIME);
        airtime.setCreatedAt(LocalDateTime.now());
        airtime.setNetworkProvider(request.getNetworkProvider());

        Transaction saved = this.transactionRepository.save(airtime);

        AirtimeResponse response = Mapper.mapToAirtimeResponse(saved, request.getNetworkProvider(), request.getPhoneNumber());

        return response;
    }

    @Override
    public TransactionHistoryResponse transactionHistory(TransactionHistoryRequest request) {
        Account account = this.accountRepository.findByAccountNumber(request.getAccountNumber());
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        TransactionHistoryResponse response = new TransactionHistoryResponse();
        response.setTransactions((List<Transaction>) account.getTransactions());
        return response;
    }
}
