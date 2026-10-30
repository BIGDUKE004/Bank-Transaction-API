package com.bank.transactionapi.data.repository;

import com.bank.transactionapi.data.models.Customer;
import com.bank.transactionapi.data.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    long countByAccountCustomerAndCreatedAtBetween(Customer customer, LocalDateTime start, LocalDateTime end);
    List<Transaction> findByAccountAccountNumberOrderByCreatedAtDesc(String accountNumber);
}
