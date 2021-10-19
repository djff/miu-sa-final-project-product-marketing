package com.miusaatega.affiliateservice.services;

import com.miusaatega.affiliateservice.model.ProductMarkupDto;
import com.miusaatega.affiliateservice.model.adapter.ProductMarkupAdapter;
import com.miusaatega.affiliateservice.repositories.ProductMarkupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@SpringBootTest
public class ProductMarkupServiceTests {

    @Autowired
    @InjectMocks
    private ProductMarkupService pmService;

    @MockBean
    private ProductMarkupRepository pmRepository;

    List<ProductMarkupDto> testData = getSampleProductMarkupData();


    @BeforeEach
    void setup() {
        Mockito.when(pmRepository.findAll()).thenReturn(testData.stream().
                map(d -> ProductMarkupAdapter.getProductMarkup(d)).
                collect(Collectors.toList()));

        Mockito.when(pmRepository.findById(testData.get(0).getId()))
                .thenReturn(Optional.of(ProductMarkupAdapter.getProductMarkup(testData.get(0))));

        Mockito.when(pmRepository.findByProductLinkId(testData.get(0).getProductLinkId()))
                .thenReturn(Optional.of(ProductMarkupAdapter.getProductMarkup(testData.get(0))));
    }


    @Test
    void testCreateMarkup() {
        var sample = testData.get(0);
        pmService.save(sample);
        ProductMarkupDto data = pmService.findById(sample.getId());
        Assertions.assertEquals(sample, data);
    }

    @Test
    void testReadAllMarkup() {
        var prods = pmService.findAll();
        Assertions.assertEquals(testData.size(), prods.size());
    }

    @Test
    void testReadMarkupByProductLink() {
        var sample = testData.get(0);
        var data = pmService.findByProductLinkId(sample.getProductLinkId());
        Assertions.assertEquals(sample, data);
    }

    List<ProductMarkupDto> getSampleProductMarkupData(){
        List<ProductMarkupDto> prods = new ArrayList<>();

        prods.add(new ProductMarkupDto("1", "1", "1", "MTox", 100.00, "FIXED", true));
        prods.add(new ProductMarkupDto("2", "2", "1", "Mjox", 120.00, "FIXED", true));
        prods.add(new ProductMarkupDto("3", "3", "1", "Mzox", 80.00, "FIXED", true));
        prods.add(new ProductMarkupDto("4", "4", "1", "NDox", 140.00, "FIXED", true));

        return prods;
    }


}
