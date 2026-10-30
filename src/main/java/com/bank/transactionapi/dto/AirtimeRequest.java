package com.bank.transactionapi.dto;

import com.bank.transactionapi.data.enums.NetworkProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AirtimeRequest {
    @NotNull
    private NetworkProvider networkProvider;
    @NotBlank
    private String sourceAccount;
    @NotNull
    @Positive
    private BigDecimal amount;
    @Pattern(regexp = "^(\\+234|0)[789][01]\\d{8}$", message = "Invalid phone number")
    private String phoneNumber;
}
