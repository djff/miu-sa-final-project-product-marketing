package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.dto.PaymentDTO;
import edu.miu.sa.paymentservice.model.PaymentType;
import edu.miu.sa.paymentservice.processor.BankService;
import edu.miu.sa.paymentservice.processor.CardService;
import edu.miu.sa.paymentservice.repository.TransactionPersistor;
import edu.miu.sa.paymentservice.utility.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionPersistor transactionPersistor;
    @Mock
    private BankService bankService;
    @Mock
    private CardService cardService;
    @Mock
    private Util utils;
    @Mock
    PaymentDTO bankPayload;
    @Mock
    PaymentDTO cardPayload;

    TransactionService makePayment;



    @BeforeEach
    public void setUp() {
        makePayment = new TransactionService(transactionPersistor, bankService, cardService, utils);
        bankPayload = new PaymentDTO(123L, "0987687", 250.50, PaymentType.BANK,
                "", "", "", "56737890", "9098874637", "Patek Milan");
        cardPayload = new PaymentDTO(123L, "0987687", 250.50, PaymentType.CARD,
                "5409887388279900", "Jason Cole", "01/24", "", "", "");
    }

    @Test
    public void canMakeCardPayment(){
        //var makePayment = new TransactionService(transactionPersistor, bankService, cardService, utils);
        var payment = makePayment.makePayment(cardPayload);
        Assertions.assertNull(payment);
    }

    @Test
    public void canMakeBankPayment(){
        //var makePayment = new TransactionService(transactionPersistor, bankService, cardService, utils);
        var payment = makePayment.makePayment(bankPayload);
        Assertions.assertEquals(false, payment.getSuccessful());
    }
}
