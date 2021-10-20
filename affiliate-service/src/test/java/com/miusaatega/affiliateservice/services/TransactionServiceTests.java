package com.miusaatega.affiliateservice.services;

import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.model.Product;
import com.miusaatega.affiliateservice.model.adapter.TransactionAdapter;
import com.miusaatega.affiliateservice.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@SpringBootTest
public class TransactionServiceTests {

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    List<TransactionDto> testData = getSampleTransactionData();


    @BeforeEach
    void setup() {
        Mockito.when(transactionRepository.findAll()).thenReturn(testData.stream().
                map(d -> TransactionAdapter.getTransaction(d)).
                collect(Collectors.toList()));

        Mockito.when(transactionRepository.findById(testData.get(0).getId()))
                .thenReturn(Optional.of(TransactionAdapter.getTransaction(testData.get(0))));

        var sample = TransactionAdapter.getTransaction(testData.get(0));
        sample.setAmount(340.00);
        Mockito.when(transactionRepository.save(sample))
                .thenReturn(sample);
    }


    @Test
    void testCreateTransaction() {
        var sample = testData.get(0);
        TransactionDto p1 = transactionService.save(sample);
        System.out.println(p1);
        Assertions.assertEquals(p1.getAmount(), 340.00);
    }

    @Test
    void testReadAllTransaction() {
        var prods = transactionService.findAll();
        Assertions.assertEquals(testData.size(), prods.size());
    }

    @Test
    void testReadTransactionById() {
        var sample = testData.get(0);
        var data = transactionService.findById(sample.getId());
        Assertions.assertEquals(sample, data);
    }

    List<TransactionDto> getSampleTransactionData(){
        List<TransactionDto> trans = new ArrayList<>();
        trans.add(new TransactionDto("1", null, getSampleProduct().stream().limit(2).collect(Collectors.toList()), LocalDateTime.of(2021, Month.OCTOBER, 17, 00, 00), "PAID"));
        trans.add(new TransactionDto("2", null, getSampleProduct().stream().skip(2).collect(Collectors.toList()), LocalDateTime.of(2021, Month.OCTOBER, 18, 00, 00), "PAID"));
        return trans;
    }

    List<Product> getSampleProduct() {
        List<Product> prods = new ArrayList<>();
        prods.add(new Product("1", 100.00, 1));
        prods.add(new Product("2", 120.00, 2));
        prods.add(new Product("3", 80.00, 1));
        return prods;
    }


}
