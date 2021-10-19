package edu.miu.inventoryservice.miusamsproductatega.repository;

import edu.miu.inventoryservice.miusamsproductatega.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
    List<Product> findByName(String name);
}
