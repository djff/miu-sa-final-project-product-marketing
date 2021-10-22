package miu.sa.accountservice.services;

import com.google.gson.Gson;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.enums.Role;
import miu.sa.accountservice.model.*;
import miu.sa.accountservice.model.entity.Affiliate;
import miu.sa.accountservice.repository.AffiliateRepository;
import miu.sa.accountservice.service.AffiliateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class AffiliateServiceTests {

    @Mock
    private AffiliateRepository repository;
    @InjectMocks
    private AffiliateService service;


    @BeforeEach
    void setup() {
    }

    @DisplayName("Test Save New Affiliate")
    @Test
    void testSave() {
        var sample = affAccounts.get(0);

        given(repository.save(any())).willReturn(sample);

        AccountDto data = (AccountDto) service.save(sample).getData();

        then(repository).should().save(any());

        Assertions.assertEquals(sample.getId(), data.getId());
    }

    @DisplayName("Test Find All Affiliates")
    @Test
    void findAll() {
        //given
        given(repository.findAll()).willReturn(affAccounts);
        //when
        List<AccountDto> list = (List<AccountDto>) service.findAll().getData();
        //then
        assertThat(3).isEqualTo(list.size());
    }

    @DisplayName("Find Affiliate By UUID")
    @Test
    void findById() {
        //given
        given(repository.findById(any(UUID.class))).willReturn(Optional.of(affAccounts.get(0)));
        //when
        AccountDto data = (AccountDto) service.findById(UUID.randomUUID()).getData();
        //then
        assertThat(data).isNotNull();
    }

    @DisplayName("Find Affiliate By Email")
    @Test
    void findByEmail() {
        //given
        given(repository.findByEmail(any())).willReturn(Optional.of(affAccounts.get(0)));
        //when
        ResponseModel data = service.findByEmail(affAccounts.get(0).getEmail());
        //then
        assertThat(data.isSuccess()).isTrue();
    }

    //mock data
    Gson g = new Gson();
    final List<Address> add = List.of(
            new Address("Kulas Light", "92998-3874", "Gwenborough", "IA", true),
            new Address("Victor Plains", "90566-7771", "Wisokyburgh", "IA", false)
    );
    final List<Address> add2 = List.of(
            new Address("Douglas Extension", "59590-4157", "McKenziehaven", "IA", true)
    );
    final List<Address> add3 = List.of(
            new Address("Hoeger Mall", "53919-4257", "South Elvis", "IA", true)
    );

    final PaymentMethod pm1 = new PaymentMethod(PaymentType.CC, g.toJson(new CreditCardPayment("1234",
            "Patricia Lebsack", "04/24")), true);
    final PaymentMethod pm2 = new PaymentMethod(PaymentType.CC, g.toJson(new CreditCardPayment("54678",
            "Clementine Bauch", "07/22")), true);
    final PaymentMethod pm3 = new PaymentMethod(PaymentType.BANK, g.toJson(new BankPayment("9012345",
            "098765", "Ervin Howell")), true);

    final List<Affiliate> affAccounts = List.of(
            new Affiliate(UUID.fromString("086347ac-32e4-11ec-8d3d-0242ac130003"), "Patricia", "Lebsack",
                    "patricia.lebsack@miu.sa", "patricia", Role.AFFILIATE.name(), true, add, List.of(pm1)),
            new Affiliate(UUID.fromString("08634b12-32e4-11ec-8d3d-0242ac130003"), "Clementine", "Bauch",
                    "clementine.bauch@miu.sa", "clementine", Role.AFFILIATE.name(), true, add2, List.of(pm2)),
            new Affiliate(UUID.fromString("08634c2a-32e4-11ec-8d3d-0242ac130003"), "Ervin", "Howell",
                    "ervin.howell@miu.sa", "ervin", Role.AFFILIATE.name(), true, add3, List.of(pm3))
    );
}
