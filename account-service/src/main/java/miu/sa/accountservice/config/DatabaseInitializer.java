package miu.sa.accountservice.config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.enums.Role;
import miu.sa.accountservice.model.Address;
import miu.sa.accountservice.model.BankPayment;
import miu.sa.accountservice.model.CreditCardPayment;
import miu.sa.accountservice.model.PaymentMethod;
import miu.sa.accountservice.model.entity.Affiliate;
import miu.sa.accountservice.model.entity.Customer;
import miu.sa.accountservice.repository.AffiliateRepository;
import miu.sa.accountservice.repository.CustomerRepository;
import miu.sa.accountservice.service.AffiliateService;
import miu.sa.accountservice.service.CustomerService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

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
    final PaymentMethod p1 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("1234", "ada lovelace", "04/24")).toString(), true);
    final PaymentMethod p2 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("54678", "alan turing", "07/22")).toString(), true);
    final PaymentMethod p3 = new PaymentMethod( PaymentType.BANK, g.toJson(new BankPayment("9012345", "098765", "dennis ritchie")).toString(), true);

    final List<Customer> custAccounts = List.of(
            new Customer(null, "Ada", "Lovelace", "ada.lovelace@miu.sa", "ada", Role.CUSTOMER.name(), true, addresses, List.of(p1)),
            new Customer(null, "Alan", "Turing", "alan.turing@miu.sa", "alan", Role.CUSTOMER.name(),true, addresses2, List.of(p2)),
            new Customer(null, "Dennis", "Ritchie", "dennis.ritchie@miu.sa", "dennis", Role.CUSTOMER.name(), true, addresses3, List.of(p3))
    );

    //Affiliates
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

    final PaymentMethod pm1 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("1234", "Patricia Lebsack", "04/24")).toString(), true);
    final PaymentMethod pm2 = new PaymentMethod( PaymentType.CC, g.toJson(new CreditCardPayment("54678", "Clementine Bauch", "07/22")).toString(), true);
    final PaymentMethod pm3 = new PaymentMethod( PaymentType.BANK, g.toJson(new BankPayment("9012345", "098765", "Ervin Howell")).toString(), true);

    final List<Affiliate> affAccounts = List.of(
            new Affiliate(null, "Patricia", "Lebsack", "patricia.lebsack@miu.sa", "patricia", Role.AFFILIATE.name(), true, add, List.of(pm1)),
            new Affiliate(null, "Clementine", "Bauch", "clementine.bauch@miu.sa", "clementine", Role.AFFILIATE.name(),true, add2, List.of(pm2)),
            new Affiliate(null, "Ervin", "Howell", "ervin.howell@miu.sa", "ervin", Role.AFFILIATE.name(), true, add3, List.of(pm3))
    );


    private final CustomerRepository repository;
    private final CustomerService service;
    private final AffiliateRepository affRepo;
    private final AffiliateService affService;
    public DatabaseInitializer(CustomerRepository repository, CustomerService service, AffiliateRepository affRepo, AffiliateService affService) {
        this.repository = repository;
        this.service = service;
        this.affRepo = affRepo;
        this.affService = affService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        repository.deleteAll();
        affRepo.deleteAll();
        log.info("Database preloading...");
        custAccounts.forEach(service::save);
        affAccounts.forEach(affService::save);
        log.info("Database preloaded::::");
        System.out.println("Customers:: " + service.findAll());
        System.out.println("Affiliates:: " + affService.findAll());
    }
}
