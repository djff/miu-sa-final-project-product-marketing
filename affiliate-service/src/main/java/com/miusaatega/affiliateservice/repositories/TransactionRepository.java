package com.miusaatega.affiliateservice.repositories;

import com.miusaatega.affiliateservice.model.entity.ProductMarkup;
import com.miusaatega.affiliateservice.model.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
