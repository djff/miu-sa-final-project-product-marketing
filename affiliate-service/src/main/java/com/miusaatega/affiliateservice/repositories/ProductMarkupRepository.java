package com.miusaatega.affiliateservice.repositories;

import com.miusaatega.affiliateservice.model.entity.ProductMarkup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMarkupRepository extends MongoRepository<ProductMarkup, String> {
    Optional<ProductMarkup> findByProductLinkId(String productLinkId);
}
