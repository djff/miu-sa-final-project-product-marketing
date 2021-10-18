package edu.miu.repository;

import edu.miu.model.Shipment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShipmentRepository extends MongoRepository<Shipment, UUID> {
}
