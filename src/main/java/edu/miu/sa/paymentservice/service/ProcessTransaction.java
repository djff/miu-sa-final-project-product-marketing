package edu.miu.sa.paymentservice.services;

import edu.miu.sa.paymentservice.dtos.*;
import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.processor.BankService;
import edu.miu.sa.paymentservice.processor.CardService;
import edu.miu.sa.paymentservice.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
public class ProcessTransaction {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private Util utils;

    public BasicResponse makePayment(PaymentDTO request){
        BasicResponse response = new BasicResponse(false);
        var payReference = utils.GenerateReference();
        transactionService.addTransaction(request, payReference);

        /*Customer customer = accountService.getCustomer(request.customerReference);
        var customerPayments = customer.getPayments().stream().filter(x -> x.getDefault())
                .collect(Collectors.toList());

        Card cardRequest = new Card();
        Bank bankRequest = new Bank();
        for (var payment : customerPayments) {
            if(payment.getDefault() && (payment.getType() == "CC")){
                cardRequest.setCardNumber(payment.getMetaData().getCardNumber());
                cardRequest.setNameOnCard(payment.getMetaData().getNameOnCard());
                cardRequest.setExpDate(payment.getMetaData().getExpDate());
            }
            else if(payment.getDefault() && (payment.getType() == "BANK")){
                bankRequest.setAccountNo(payment.getMetaData().getAccountNo());
                bankRequest.setRoutingNo(payment.getMetaData().getRoutingNo());
                bankRequest.setAccountName(payment.getMetaData().getAccountName());
            }
        }*/

        Card cardRequest = new Card();
        Bank bankRequest = new Bank();

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

        //TO DO: update payment with response from transaction services
        var transactionDetails = transactionService.findTransaction(payReference);
        if(!response.getSuccessful()){
            transactionDetails.responseCode = response.getResponseCode();
            transactionDetails.responseTime = LocalDateTime.now();
            transactionDetails.status = PaymentStatus.FAILED;
            transactionService.updateTransaction(transactionDetails);

            response.setResponseCode("99");
            response.setResponseDescription("Payment not successful");
            return response;
        }

        transactionDetails.responseCode = response.getResponseCode();
        transactionDetails.responseTime = LocalDateTime.now();
        transactionDetails.status = PaymentStatus.SUCCESSFUL;
        transactionService.updateTransaction(transactionDetails);

        response.setResponseCode("00");
        response.setResponseDescription("Successful");

        return response;
    }
}
