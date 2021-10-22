package miu.sa.accountservice.services;

import miu.sa.accountservice.repository.CustomerRepository;
import miu.sa.accountservice.service.CustomerService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    @InjectMocks
    private CustomerService service;

    @MockBean
    private CustomerRepository repository;
}
