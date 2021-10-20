package com.miusaatega.affiliateservice.model.adapter;

import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.model.entity.Transaction;

public class TransactionAdapter {
    public static TransactionDto getTransactionDTO(Transaction data) {
        if(data == null) return null;
        var dataDto = new TransactionDto(data.getId(),
                data.getAmount(), data.getProducts(), data.getTransDate(), data.getPaymentStatus());
        return dataDto;
    }

    public static Transaction getTransaction(TransactionDto dataDTO) {
        if(dataDTO == null) return null;
        var data = new Transaction(dataDTO.getId(),
                dataDTO.getAmount(), dataDTO.getProducts(), dataDTO.getTransDate(), dataDTO.getPaymentStatus());
        return data;
    }
}
