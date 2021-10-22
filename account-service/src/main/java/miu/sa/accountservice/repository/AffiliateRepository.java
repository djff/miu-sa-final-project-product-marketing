package miu.sa.accountservice.repository;

import miu.sa.accountservice.model.entity.Affiliate;
import miu.sa.accountservice.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AffiliateRepository extends MongoRepository<Affiliate, UUID> {
    Optional<Affiliate> findByEmail(String email);
}
