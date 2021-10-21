package com.miusaatega.affiliateservice;

import com.miusaatega.affiliateservice.repositories.ProductMarkupRepository;
import com.miusaatega.affiliateservice.repositories.TransactionRepository;
import com.miusaatega.affiliateservice.services.ProductMarkupService;
import com.miusaatega.affiliateservice.services.TransactionServiceTests;
import org.junit.internal.builders.SuiteMethodBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.Suite;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ActiveProfiles("test")
@SpringBootTest()
class AffiliateServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private ProductMarkupRepository pmRepository;

	@MockBean
	private TransactionRepository transactionRepository;

}
