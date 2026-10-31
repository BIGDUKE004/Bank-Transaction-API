package com.bank.transactionapi;

import com.bank.transactionapi.data.enums.CustomerType;
import com.bank.transactionapi.data.models.Customer;
import com.bank.transactionapi.discount.DiscountRuleImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DiscountRuleTest {

    private final DiscountRuleImpl discountRule = new DiscountRuleImpl();

    @ParameterizedTest
    @CsvSource({
            "BUSINESS, 100000, 3, 0",
            "BUSINESS, 150000, 3, 0",
            "BUSINESS, 200000, 2, 0",
            "BUSINESS, 200000, 3, 54000",
            "RETAIL, 40000, 3, 0",
            "RETAIL, 50000, 3, 0",
            "RETAIL, 60000, 2, 0",
            "RETAIL, 60000, 3, 10800"
    })
    void shouldCalculateDiscountBasedOnCustomerType(
            String type,
            String amount,
            long count,
            String expectedDiscount) {

        Customer customer = new Customer();
        customer.setType(CustomerType.valueOf(type));

        BigDecimal actual = discountRule.discount(
                customer,
                new BigDecimal(amount),
                count
        );

        assertEquals(
                0,
                actual.compareTo(new BigDecimal(expectedDiscount)),
                "Failed for type=" + type
                        + ", amount=" + amount
                        + ", count=" + count
        );
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 100000, 10000",
            "true, 2, 100000, 10000",
            "true, 3, 100000, 0",
            "false, 0, 100000, 0",
            "false, 3, 100000, 0"
    })
    void shouldCalculateLoyaltyDiscount(
            boolean overFourYears,
            long count,
            String amount,
            String expectedDiscount) {

        BigDecimal actual = discountRule.discount(
                overFourYears,
                count,
                new BigDecimal(amount)
        );

        assertEquals(
                0,
                actual.compareTo(new BigDecimal(expectedDiscount)),
                "Failed for overFourYears=" + overFourYears
                        + ", count=" + count
                        + ", amount=" + amount
        );
    }

}
