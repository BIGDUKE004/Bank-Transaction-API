package com.bank.transactionapi.discount;

import com.bank.transactionapi.data.enums.CustomerType;
import com.bank.transactionapi.data.models.Customer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountRuleImpl implements DiscountRule {
    @Override
    public BigDecimal discount(Customer customer, BigDecimal amount, Long count) {
        BigDecimal userDiscount = null;
        if(customer.getType() == CustomerType.BUSINESS){
            if(amount.compareTo(new BigDecimal("150000")) > 0){
                if(count >= 3){
                    userDiscount.add(businessDiscount(amount));
                }
            }
        } else if(customer.getType() == CustomerType.RETAIL){
            if(amount.compareTo(new BigDecimal("50000")) > 0){
                if(count >= 3){
                    userDiscount.add(retailDiscount(amount));
                }
            }
        }
        return userDiscount;
    }


    @Override
    public BigDecimal businessDiscount(BigDecimal amount) {
        int percent = 27;
        BigDecimal percentValue = BigDecimal.valueOf(percent);
        BigDecimal discountAmount = amount.multiply(percentValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal amountCharged = amount.subtract(discountAmount);
        return amountCharged;
    }

    @Override
    public BigDecimal retailDiscount(BigDecimal amount) {
        int percent = 18;
        BigDecimal percentValue = BigDecimal.valueOf(percent);
        BigDecimal discountAmount = amount.multiply(percentValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal amountCharged = amount.subtract(discountAmount);
        return amountCharged;
    }

    @Override
    public BigDecimal discount(boolean overFourYears, long count, BigDecimal amount) {
        if (overFourYears && count < 3) {
            int percent = 10;
            BigDecimal percentValue = BigDecimal.valueOf(percent);
            BigDecimal discountAmount = amount.multiply(percentValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal amountCharged = amount.subtract(discountAmount);
            return amountCharged;
        }
        return null;
    }
}
