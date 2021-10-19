package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.BasicResponse;
import edu.miu.sa.paymentservice.dtos.PaymentDTO;
import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.model.Transaction;
import edu.miu.sa.paymentservice.repository.PaymentRepository;
import edu.miu.sa.paymentservice.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private PaymentRepository paymentRepository;

    public BasicResponse addTransaction(PaymentDTO request, String paymentReference){
        BasicResponse response = new BasicResponse(false);
        Transaction trans = new Transaction();
        trans.customerReference = request.getCustomerReference();
        trans.paymentReference = paymentReference;
        trans.orderNumber = request.getOrderNumber();
        trans.amount = request.getAmount();
        trans.type = request.getType();
        trans.status = PaymentStatus.PENDING;
        trans.requestTime = LocalDateTime.now();
        trans.dateCreated = LocalDateTime.now();

        var t = paymentRepository.save(trans);

        if(t == null){
            return response;
        }
        response.setSuccessful(true);
        return response;
    }

    public BasicResponse updateTransaction(Transaction transaction){
        BasicResponse response = new BasicResponse(false);
        var trans = paymentRepository.findById(transaction.getId());

        if(trans == null){
            transaction.responseTime = LocalDateTime.now();
            transaction.responseCode = "400";
            paymentRepository.save(transaction);
            return response;
        }

        transaction.responseTime = LocalDateTime.now();
        transaction.responseCode = "00";
        paymentRepository.save(transaction);
        return response;
    }

    public Transaction findTransaction(String paymentReference){
        Transaction transaction = paymentRepository.findByPaymentReference(paymentReference);
        return transaction;
    }
}
