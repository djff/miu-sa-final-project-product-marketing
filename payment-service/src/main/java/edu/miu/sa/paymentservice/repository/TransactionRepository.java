package edu.miu.sa.paymentservice.repository;

import edu.miu.sa.paymentservice.model.Transaction;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Transaction findByPaymentReference(String paymentReference);
}
