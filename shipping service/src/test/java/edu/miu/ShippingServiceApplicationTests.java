package edu.miu;

import edu.miu.model.ResponseFormat;
import edu.miu.model.Shipment;
import edu.miu.repository.ShipmentRepository;
import edu.miu.service.MyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
class ShippingServiceApplicationTests {
    @Autowired
    @InjectMocks
    private MyService myservice;
    @MockBean
    private ShipmentRepository shipmentRepository;
    List<Shipment> testData = getSampleResposeData();

    private List<Shipment> getSampleResposeData() {
        List<Shipment> data = new ArrayList<>();
        data.add(new Shipment(UUID.randomUUID(),"DHL34523","shipped!"));
        data.add(new Shipment(UUID.randomUUID(),"DHL43523","shipped!"));
        return data;
    }

    @BeforeEach
    void setup() {
        Mockito.when(shipmentRepository.findAll()).thenReturn(testData);
        Mockito.when(shipmentRepository.save(testData.get(0))).thenReturn(testData.get(0));
        Mockito.when(shipmentRepository.findById(testData.get(0).getOrderId()))
                .thenReturn(Optional.of(testData.get(0)));
    }

    @Test
    void testCreateShipment() {
        var sample = testData.get(0);
//        shipmentRepository.save(sample);
        myservice.processOrder(sample.getOrderId());
        Shipment p1 = myservice.findShipment(sample.getOrderId());
        Assertions.assertNotNull(p1);
    }



}
