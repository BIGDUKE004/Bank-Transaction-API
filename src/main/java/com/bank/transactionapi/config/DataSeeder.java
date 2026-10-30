package com.bank.transactionapi.config;

import com.bank.transactionapi.data.enums.CustomerType;
import com.bank.transactionapi.data.models.Account;
import com.bank.transactionapi.data.models.Customer;
import com.bank.transactionapi.data.repository.AccountRepository;
import com.bank.transactionapi.data.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    public DataSeeder(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() > 0) {
            return;
        }

        Customer ada = new Customer();
        ada.setName("Ada");
        ada.setType(CustomerType.RETAIL);
        ada.setJoinedDate(LocalDate.now().minusYears(1));
        Customer savedAda = customerRepository.save(ada);

        Account account = new Account();
        account.setAccountNumber("0123456789");
        account.setCustomer(savedAda);
        accountRepository.save(account);

        Customer chidi = new Customer();
        chidi.setName("Chidi’s Ltd");
        chidi.setType(CustomerType.BUSINESS);
        chidi.setJoinedDate(LocalDate.now().minusYears(1));
        Customer savedChidi = customerRepository.save(chidi);

        Account chidiAccount = new Account();
        chidiAccount.setAccountNumber("3333333333");
        chidiAccount.setCustomer(savedChidi);
        accountRepository.save(chidiAccount);

        Customer bola = new Customer();
        bola.setName("Bola");
        bola.setType(CustomerType.RETAIL);
        bola.setJoinedDate(LocalDate.now().minusYears(1));
        Customer savedBola = customerRepository.save(chidi);

        Account bolaAccount = new Account();
        bolaAccount.setAccountNumber("1111111111");
        bolaAccount.setCustomer(savedBola);
        accountRepository.save(bolaAccount);

        Account bolaSecondAccount = new Account();
        bolaSecondAccount.setAccountNumber("2222222222");
        bolaSecondAccount.setCustomer(savedBola);
        accountRepository.save(bolaSecondAccount);
    }
}
