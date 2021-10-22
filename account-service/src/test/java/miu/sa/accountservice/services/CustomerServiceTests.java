package miu.sa.accountservice.services;

import com.google.gson.Gson;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.enums.Role;
import miu.sa.accountservice.model.*;
import miu.sa.accountservice.model.entity.Customer;
import miu.sa.accountservice.repository.CustomerRepository;
import miu.sa.accountservice.service.CustomerService;
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
public class CustomerServiceTests {

    @Mock
    private CustomerRepository repository;
    @InjectMocks
    private CustomerService service;


    @BeforeEach
    void setup() {
    }

    @DisplayName("Test Save New Customer")
    @Test
    void testSave() {
        var sample = custAccounts.get(0);
        //given
        given(repository.save(any())).willReturn(sample);
        //when
        AccountDto data = (AccountDto) service.save(sample).getData();
        //then
        then(repository).should().save(any());
        Assertions.assertEquals(sample.getId(), data.getId());
    }

    @DisplayName("Test Find All Customers")
    @Test
    void findAll() {
        //given
        given(repository.findAll()).willReturn(custAccounts);
        //when
        List<AccountDto> list = (List<AccountDto>) service.findAll().getData();
        //then
        assertThat(3).isEqualTo(list.size());
    }

    @DisplayName("Find Customer By UUID")
    @Test
    void findById() {
        //given
        given(repository.findById(any(UUID.class))).willReturn(Optional.of(custAccounts.get(0)));
        //when
        AccountDto data = (AccountDto) service.findById(UUID.randomUUID()).getData();
        //then
        assertThat(data).isNotNull();
    }

    @DisplayName("Find Affiliate By Email")
    @Test
    void findByEmail() {
        //given
        given(repository.findByEmail(any())).willReturn(Optional.of(custAccounts.get(0)));
        //when
        ResponseModel data = service.findByEmail(custAccounts.get(0).getEmail());
        //then
        assertThat(data.isSuccess()).isTrue();
    }
    
    //mock data
    final List<Address> addresses = List.of(
            new Address( "1000 N 4th st", "52557", "Fairfield", "IA", true),
            new Address( "1000 N 4th st", "52557", "Fairfield", "IA", false)
    );

    final List<Address> addresses2 = List.of(
            new Address( "1000 N 4th st", "52557", "Fairfield", "IA", true),
            new Address( "1000 N 4th st", "52557", "Fairfield", "IA", false)
    );
    final List<Address> addresses3 = List.of(
            new Address( "1000 N 4th st", "52557", "Fairfield", "IA", true),
            new Address( "1000 N 4th st", "52557", "Fairfield", "IA", false)
    );

    Gson g = new Gson();
    final PaymentMethod p1 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("1234", "ada lovelace", "04/24")), true);
    final PaymentMethod p2 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("54678", "alan turing", "07/22")), true);
    final PaymentMethod p3 = new PaymentMethod( PaymentType.BANK, g.toJson(new BankPayment("9012345", "098765", "dennis ritchie")), true);

    final List<Customer> custAccounts = List.of(
            new Customer(null, "Ada", "Lovelace", "ada.lovelace@miu.sa", "ada", Role.CUSTOMER.name(), true, addresses, List.of(p1)),
            new Customer(null, "Alan", "Turing", "alan.turing@miu.sa", "alan", Role.CUSTOMER.name(),true, addresses2, List.of(p2)),
            new Customer(null, "Dennis", "Ritchie", "dennis.ritchie@miu.sa", "dennis", Role.CUSTOMER.name(), true, addresses3, List.of(p3))
    );

}
