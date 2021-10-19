package edu.miu.sa.paymentservice.repository;

import edu.miu.sa.paymentservice.dto.BasicResponse;
import edu.miu.sa.paymentservice.dto.PaymentDTO;
import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.model.Transaction;
import edu.miu.sa.paymentservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionPersistor {
    @Autowired
    private TransactionRepository transactionRepository;

    public BasicResponse addTransaction(PaymentDTO request, String paymentReference){
        BasicResponse response = new BasicResponse(false);
        Transaction trans = new Transaction();
        //trans.id = UUID.randomUUID();
        trans.customerReference = request.getCustomerReference();
        trans.paymentReference = paymentReference;
        trans.orderNumber = request.getOrderNumber();
        trans.amount = request.getAmount();
        trans.type = request.getType();
        trans.status = PaymentStatus.PENDING;
        trans.requestTime = LocalDateTime.now();
        trans.dateCreated = LocalDateTime.now();

        var t = transactionRepository.insert(trans);

        if(t == null){
            return response;
        }
        response.setSuccessful(true);
        return response;
    }

    public BasicResponse updateTransaction(Transaction transaction, boolean status){
        BasicResponse response = new BasicResponse(false);
        var trans = transactionRepository.findByPaymentReference(transaction.paymentReference);

        if(trans != null && !status){
            transaction.responseTime = LocalDateTime.now();
            transaction.responseCode = "400";
            transactionRepository.save(transaction);
            return response;
        }

        transaction.responseTime = LocalDateTime.now();
        transaction.responseCode = "00";
        transactionRepository.save(transaction);
        response.setSuccessful(true);
        return response;
    }

    public Transaction findTransaction(String paymentReference){
        Transaction transaction = transactionRepository.findByPaymentReference(paymentReference);
        return transaction;
    }
}
