package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.dto.Bank;
import edu.miu.sa.paymentservice.dto.BasicResponse;
import edu.miu.sa.paymentservice.dto.Card;
import edu.miu.sa.paymentservice.dto.*;
import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.processor.BankService;
import edu.miu.sa.paymentservice.processor.CardService;
import edu.miu.sa.paymentservice.repository.TransactionPersistor;
import edu.miu.sa.paymentservice.utility.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class TransactionService {
    @Autowired
    private TransactionPersistor transactionPersistor;
    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private Util utils;

    public BasicResponse makePayment(PaymentDTO request){
        BasicResponse response = new BasicResponse(false);
        var payReference = utils.GenerateReference();
        transactionPersistor.addTransaction(request, payReference);

        var cardRequest = new Card();
        var bankRequest = new Bank();

        switch(request.getType()){
            case CARD:
                cardRequest.setCardNumber(request.getCardNumber());
                cardRequest.setNameOnCard(request.getNameOnCard());
                cardRequest.setExpDate(request.getExpDate());
                cardRequest.setAmount(request.getAmount());
                System.out.println(request.getAmount());
                response = cardService.payByCard(cardRequest);
                System.out.println(response.getSuccessful());
                break;
            case BANK:
                bankRequest.setAccountNo(request.getAccountNo());
                bankRequest.setRoutingNo(request.getRoutingNo());
                bankRequest.setAccountName(request.getAccountName());
                bankRequest.setAmount(request.getAmount());
                response = bankService.payByBank(bankRequest);
                break;
            default:
                break;
        }

        //TODO: update payment with response from transaction services
        var transactionDetails = transactionPersistor.findTransaction(payReference);
        if(!response.getSuccessful()){
            transactionDetails.responseCode = response.getResponseCode();
            transactionDetails.responseTime = LocalDateTime.now();
            transactionDetails.status = PaymentStatus.FAILED;
            transactionPersistor.updateTransaction(transactionDetails);

            response.setResponseCode("99");
            response.setResponseDescription("Payment NOT successful");
            response.setPaymentResponse(new PaymentResponse(request.getOrderNumber(), payReference));
            return response;
        }

        transactionDetails.responseCode = response.getResponseCode();
        transactionDetails.responseTime = LocalDateTime.now();
        transactionDetails.status = PaymentStatus.SUCCESSFUL;
        transactionPersistor.updateTransaction(transactionDetails);

        response.setSuccessful(true);
        response.setResponseCode("00");
        response.setResponseDescription("Successful");
        response.setPaymentResponse(new PaymentResponse(request.getOrderNumber(), payReference));

        return response;
    }
}
