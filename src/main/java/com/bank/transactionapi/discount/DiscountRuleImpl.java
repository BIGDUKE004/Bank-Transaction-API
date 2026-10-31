package com.bank.transactionapi.discount;

import com.bank.transactionapi.data.enums.CustomerType;
import com.bank.transactionapi.data.models.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Component
public class DiscountRuleImpl implements DiscountRule {
    @Override
    public BigDecimal discount(Customer customer, BigDecimal amount, Long count) {
        BigDecimal userDiscount = BigDecimal.ZERO;
        if(customer.getType() == CustomerType.BUSINESS){
            if(amount.compareTo(new BigDecimal("150000")) > 0){
                if(count >= 3){
                    userDiscount = businessDiscount(amount);
                }
            }
        } else if(customer.getType() == CustomerType.RETAIL){
            if(amount.compareTo(new BigDecimal("50000")) > 0){
                if(count >= 3){
                    userDiscount = retailDiscount(amount);
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
        return discountAmount;
    }

    @Override
    public BigDecimal retailDiscount(BigDecimal amount) {
        int percent = 18;
        BigDecimal percentValue = BigDecimal.valueOf(percent);
        BigDecimal discountAmount = amount.multiply(percentValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return discountAmount;
    }

    @Override
    public BigDecimal discount(boolean overFourYears, long count, BigDecimal amount) {
        if (overFourYears && count < 3) {
            int percent = 10;
            BigDecimal percentValue = BigDecimal.valueOf(percent);
            BigDecimal discountAmount = amount.multiply(percentValue).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            return discountAmount;
        }
        return BigDecimal.ZERO;
    }
}
