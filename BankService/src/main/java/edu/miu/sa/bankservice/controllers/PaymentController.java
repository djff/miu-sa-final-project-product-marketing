package edu.miu.sa.bankservice.controllers;

import edu.miu.sa.bankservice.dto.BasicResponse;
import edu.miu.sa.bankservice.dto.PaymentDTO;
import edu.miu.sa.bankservice.services.BankPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bank")
public class PaymentController {
    @Autowired
    BankPaymentService bankPaymentService;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    @Async
    public ResponseEntity<BasicResponse> cardPayment(@RequestParam String accountNo,
                                                     @RequestParam String routingNo,
                                                     @RequestParam String accountName,
                                                     @RequestParam Double amount){

        PaymentDTO request = new PaymentDTO();
        request.setAccountNumber(accountNo);
        request.setRoutingNo(routingNo);
        request.setAccountName(accountName);
        request.setAmount(amount);

        System.out.println(request.getAccountName());
        BasicResponse response = bankPaymentService.pay(request);

        if(!response.getSuccessful()){
            return new ResponseEntity<BasicResponse>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
    }
}
