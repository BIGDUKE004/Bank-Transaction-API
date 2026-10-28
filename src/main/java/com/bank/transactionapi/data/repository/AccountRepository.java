package com.bank.transactionapi.data.repository;

import com.bank.transactionapi.data.models.Account;
import com.bank.transactionapi.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Customer findByAccountNumber(String account);
}
