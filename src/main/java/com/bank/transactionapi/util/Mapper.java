package com.bank.transactionapi.util;

import com.bank.transactionapi.data.enums.NetworkProvider;
import com.bank.transactionapi.data.models.Transaction;
import com.bank.transactionapi.dto.AirtimeResponse;
import com.bank.transactionapi.dto.TransferResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Mapper {
    public static TransferResponse mapToTransferResponse(Transaction saved, String accountDestination) {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setAmount(saved.getAmount());
        transferResponse.setType(saved.getType());
        transferResponse.setId(saved.getId());
        transferResponse.setCreatedAt(saved.getCreatedAt());
        transferResponse.setDiscountPercentage(saved.getDiscountAmount().divide(saved.getAmount(), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .setScale(0, RoundingMode.HALF_UP)
                        .intValue());
        transferResponse.setSourceAccount(saved.getAccount().getAccountNumber());
        transferResponse.setDestinationAccount(accountDestination);
        return transferResponse;
    }

    public static AirtimeResponse mapToAirtimeResponse(Transaction saved, NetworkProvider networkProvider, String phoneNumber) {
        AirtimeResponse airtimeResponse = new AirtimeResponse();
        airtimeResponse.setCreatedAt(saved.getCreatedAt());
        airtimeResponse.setAmount(saved.getAmount());
        airtimeResponse.setNetworkProvider(networkProvider);
        airtimeResponse.setSourceAccount(saved.getAccount().getAccountNumber());
        airtimeResponse.setPhoneNumber(phoneNumber);
        return airtimeResponse;
    }
}
