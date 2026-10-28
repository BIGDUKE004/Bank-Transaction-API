package com.bank.transactionapi.discount;

import com.bank.transactionapi.data.models.Customer;
import com.bank.transactionapi.dto.TransferRequest;

import java.math.BigDecimal;

public interface DiscountRule {
    BigDecimal discount (Customer customer, BigDecimal amount, Long count);
    BigDecimal businessDiscount(BigDecimal amount);
    BigDecimal retailDiscount(BigDecimal amount);
    BigDecimal discount(boolean overFourYears, long count, BigDecimal amount);
}
