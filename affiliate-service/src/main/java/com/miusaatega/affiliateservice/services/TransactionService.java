package com.miusaatega.affiliateservice.services;

import com.miusaatega.affiliateservice.exceptions.ResourceNotFoundException;
import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.model.adapter.TransactionAdapter;
import com.miusaatega.affiliateservice.model.entity.Transaction;
import com.miusaatega.affiliateservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDto save(TransactionDto paramDTO){
        var param = TransactionAdapter.getTransaction(paramDTO);
        param.setAmount(param.getProducts().stream().
                reduce(0.00, (total, p2) -> total + (p2.getPrice() * p2.getQuantity()), Double::sum));
        return TransactionAdapter.getTransactionDTO(transactionRepository.save(param));
    }

    public List<TransactionDto> findAll() {
        return transactionRepository.findAll().stream().map(p -> TransactionAdapter.getTransactionDTO(p))
                .collect(Collectors.toList());
    }

    public TransactionDto findById(String id) {
        var data =  transactionRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("item " + id + " not found")
                );

        return TransactionAdapter.getTransactionDTO(data);
    }

    public void deleteAll() {
        transactionRepository.deleteAll();
    }


}
