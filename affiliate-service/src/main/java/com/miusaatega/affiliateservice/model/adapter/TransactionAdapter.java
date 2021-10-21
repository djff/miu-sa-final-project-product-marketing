package com.miusaatega.affiliateservice.model.adapter;

import com.miusaatega.affiliateservice.model.OrderSystemTransactionDto;
import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.model.entity.Transaction;

public class TransactionAdapter {
    public static TransactionDto getTransactionDTO(Transaction data) {
        if(data == null) return null;
        var dataDto = new TransactionDto(data.getId(), data.getTransRef(),
                data.getAmount(), data.getProducts(), data.getTransDate(), data.getPaymentStatus());
        return dataDto;
    }

    public static Transaction getTransaction(TransactionDto dataDTO) {
        if(dataDTO == null) return null;
        var data = new Transaction(dataDTO.getId(), dataDTO.getTransRef(),
                dataDTO.getAmount(), dataDTO.getProducts(), dataDTO.getTransDate(), dataDTO.getPaymentStatus());
        return data;
    }

    public static TransactionDto getTrasactionDtoFromOrder(OrderSystemTransactionDto data){
        if(data == null) return null;
        var dataDto = new TransactionDto(null, data.getOrderId(),
                data.getAmount(), data.getProductList(), data.getDateCreated(), data.getPaymentStatus());
        return dataDto;
    }
}
