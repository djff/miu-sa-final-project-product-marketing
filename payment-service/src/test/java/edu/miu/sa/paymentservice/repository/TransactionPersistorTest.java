package edu.miu.sa.paymentservice.repository;

import edu.miu.sa.paymentservice.dto.PaymentDTO;
import edu.miu.sa.paymentservice.model.PaymentStatus;
import edu.miu.sa.paymentservice.model.PaymentType;
import edu.miu.sa.paymentservice.model.Transaction;
import edu.miu.sa.paymentservice.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionPersistorTest {


    private TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);

    Transaction trans;

    TransactionPersistor saveTransaction;


    @Test
    void addTransaction() {
        saveTransaction = new TransactionPersistor(transactionRepository);
//        trans = new Transaction(UUID.randomUUID(), "334536626", "3536267w", 200.00, PaymentType.CARD,
//                PaymentStatus.PENDING, LocalDateTime.now(),"00", LocalDateTime.now(), LocalDateTime.now());
//        var actual = transactionRepository.insert(trans);
//        Assertions.assertNull(actual);
    }

    @Test
    void updateTransaction() {
    }

    @Test
    void findTransaction() {
    }
}