package edu.miu.sa.paymentservice.utility;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Util {

    @Async
    public String GenerateReference(){
        return UUID.randomUUID().toString();
    }
}
