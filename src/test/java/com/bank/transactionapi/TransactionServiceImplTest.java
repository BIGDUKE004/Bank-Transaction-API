package com.bank.transactionapi;

import com.bank.transactionapi.data.models.Account;
import com.bank.transactionapi.data.models.Customer;
import com.bank.transactionapi.data.repository.AccountRepository;
import com.bank.transactionapi.data.repository.TransactionRepository;
import com.bank.transactionapi.discount.DiscountRuleImpl;
import com.bank.transactionapi.dto.TransferRequest;
import com.bank.transactionapi.exception.InvalidAmountException;
import com.bank.transactionapi.service.TransactionServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private DiscountRuleImpl discountRule;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Account account;

    @BeforeEach
    void setUp() {
        Customer customer = new Customer();

        account = new Account();
        account.setAccountNumber("0123456789");
        account.setCustomer(customer);

        customer.setJoinedDate(java.time.LocalDate.now());
    }

    @ParameterizedTest
    @CsvSource({
            "1000, true",
            "0, false",
            "-500, false",
            "0.01, true",
            "5000000, true",
            "5000000.01, true",
            "4999999.99, true",
            "250000, true"
    })
    void shouldValidateAmount(String amount, boolean isValid) {

        BigDecimal value = new BigDecimal(amount);

        TransferRequest request = new TransferRequest();
        request.setSourceAccount("0123456789");
        request.setDestinationAccount("9999999999");
        request.setAmount(value);

        when(accountRepository.findByAccountNumber("0123456789"))
                .thenReturn(account);

        if (isValid) {
            when(transactionRepository
                    .countByAccountCustomerAndCreatedAtBetween(
                            eq(account.getCustomer()),
                            any(LocalDateTime.class),
                            any(LocalDateTime.class)))
                    .thenReturn(0L);

            when(discountRule.discount(
                    any(Customer.class),
                    any(BigDecimal.class),
                    anyLong()))
                    .thenReturn(BigDecimal.ZERO);

            when(discountRule.discount(
                    anyBoolean(),
                    anyLong(),
                    any(BigDecimal.class)))
                    .thenReturn(BigDecimal.ZERO);

            when(transactionRepository.save(any()))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            assertDoesNotThrow(() -> transactionService.transfer(request));

        } else {
            assertThrows(
                    InvalidAmountException.class,
                    () -> transactionService.transfer(request)
            );
        }
    }
}
