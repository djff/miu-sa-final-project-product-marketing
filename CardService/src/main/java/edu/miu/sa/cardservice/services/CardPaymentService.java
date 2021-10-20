package edu.miu.sa.cardservice.services;

import edu.miu.sa.cardservice.dto.BasicResponse;
import edu.miu.sa.cardservice.dto.PaymentDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentService {
    private static String[] respCodes = {"00", "01"};
    private static String[] respMsgs = {"Successful", "Amount must be $20 or more"};

    @Async
    public BasicResponse pay(PaymentDTO request){
        BasicResponse response = new BasicResponse(false);
        if(request.getAmount() < 20){
            response.setResponseCode(respCodes[1]);
            response.setResponseDescription(respMsgs[1]);
        }
        else{
            response.setSuccessful(true);
            response.setResponseCode(respCodes[0]);
            response.setResponseDescription(respMsgs[0]);
        }
        return response;
    }
}
