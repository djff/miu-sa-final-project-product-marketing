package edu.miu.sa.cardservice.controller;

import edu.miu.sa.cardservice.dto.BasicResponse;
import edu.miu.sa.cardservice.dto.PaymentDTO;
import edu.miu.sa.cardservice.services.CardPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/card")
public class PaymentController {

    @Autowired
    private CardPaymentService cardPaymentService;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    @Async
    public ResponseEntity<BasicResponse> cardPayment(@RequestParam String cardNumber,
                                                     @RequestParam String nameOnCard,
                                                     @RequestParam String expDate,
                                                     @RequestParam Double amount){

        PaymentDTO request = new PaymentDTO();
        request.setCardNumber(cardNumber);
        request.setNameOnCard(nameOnCard);
        request.setExpDate(expDate);
        request.setAmount(amount);
        BasicResponse response = cardPaymentService.pay(request);
        System.out.println("HHHHHHHHHHHHHHHH" + request.getNameOnCard());
        if(!response.getSuccessful()){

            return new ResponseEntity<BasicResponse>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
    }
}
