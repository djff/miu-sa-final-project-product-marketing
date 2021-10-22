package miu.sa.accountservice.services;

import com.google.gson.Gson;
import miu.sa.accountservice.AccountServiceApplication;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.enums.Role;
import miu.sa.accountservice.model.*;
import miu.sa.accountservice.model.entity.Affiliate;
import miu.sa.accountservice.repository.AffiliateRepository;
import miu.sa.accountservice.service.AffiliateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AffiliateServiceTests {

    @Autowired
    @InjectMocks
    private AffiliateService service;

    @MockBean
    private AffiliateRepository repository;


    @BeforeEach
    void setup() {
        Mockito.when(repository.findAll()).thenReturn(affAccounts.stream()
                .map(a -> new Affiliate(a.getId(), a.getFirstName(), a.getLastName(), a.getEmail(), a.getPassword(),
                        a.getRole(), a.isActive(), a.getAddresses(), a.getPayments()))
                .collect(Collectors.toList()));

        Mockito.when(repository.findById(affAccounts.get(0).getId()))
                .thenReturn(Optional.of(new Affiliate(affAccounts.get(0).getId(), affAccounts.get(0).getFirstName(),
                        affAccounts.get(0).getLastName(), affAccounts.get(0).getEmail(), affAccounts.get(0).getPassword(),
                        affAccounts.get(0).getRole(), affAccounts.get(0).isActive(), affAccounts.get(0).getAddresses(),
                        affAccounts.get(0).getPayments())));

        Mockito.when(repository.findByEmail(affAccounts.get(0).getEmail()))
                .thenReturn(Optional.of(new Affiliate(affAccounts.get(0).getId(), affAccounts.get(0).getFirstName(),
                affAccounts.get(0).getLastName(), affAccounts.get(0).getEmail(), affAccounts.get(0).getPassword(),
                affAccounts.get(0).getRole(), affAccounts.get(0).isActive(), affAccounts.get(0).getAddresses(),
                affAccounts.get(0).getPayments())));
    }


    @Test
    void testSave() {
        var sample = affAccounts.get(0);
        service.save(sample);
        ResponseModel data = service.findById(sample.getId());
        Assertions.assertEquals(sample, data.getData());
    }

//    @Test
//    void testFindById() {
//        var prods = pmService.findAll();
//        Assertions.assertEquals(testData.size(), prods.size());
//    }
//
//    @Test
//    void testFindByEmail() {
//        var sample = testData.get(0);
//        var data = pmService.findByProductLinkId(sample.getProductLinkId());
//        Assertions.assertEquals(sample, data);
//    }







    //mock data
    Gson g = new Gson();
    final List<Address> add = List.of(
            new Address( "Kulas Light", "92998-3874", "Gwenborough", "IA", true),
            new Address( "Victor Plains", "90566-7771", "Wisokyburgh", "IA", false)
    );
    final List<Address> add2 = List.of(
            new Address( "Douglas Extension", "59590-4157", "McKenziehaven", "IA", true)
    );
    final List<Address> add3 = List.of(
            new Address( "Hoeger Mall", "53919-4257", "South Elvis", "IA", true)
    );

    final PaymentMethod pm1 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("1234",
            "Patricia Lebsack", "04/24")).toString(), true);
    final PaymentMethod pm2 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("54678",
            "Clementine Bauch", "07/22")).toString(), true);
    final PaymentMethod pm3 = new PaymentMethod( PaymentType.BANK, g.toJson(new BankPayment("9012345",
            "098765", "Ervin Howell")).toString(), true);

    final List<Affiliate> affAccounts = List.of(
            new Affiliate(UUID.fromString("086347ac-32e4-11ec-8d3d-0242ac130003"), "Patricia", "Lebsack",
                    "patricia.lebsack@miu.sa", "patricia", Role.AFFILIATE.name(), true, add, List.of(pm1)),
            new Affiliate(UUID.fromString("08634b12-32e4-11ec-8d3d-0242ac130003"), "Clementine", "Bauch",
                    "clementine.bauch@miu.sa", "clementine", Role.AFFILIATE.name(),true, add2, List.of(pm2)),
            new Affiliate(UUID.fromString("08634c2a-32e4-11ec-8d3d-0242ac130003"), "Ervin", "Howell",
                    "ervin.howell@miu.sa", "ervin", Role.AFFILIATE.name(), true, add3, List.of(pm3))
    );
}
