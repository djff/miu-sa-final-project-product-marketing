package com.miusaatega.affiliateservice.services;

import com.miusaatega.affiliateservice.exceptions.ResourceNotFoundException;
import com.miusaatega.affiliateservice.model.ProductMarkupDto;
import com.miusaatega.affiliateservice.model.adapter.ProductMarkupAdapter;
import com.miusaatega.affiliateservice.model.entity.ProductMarkup;
import com.miusaatega.affiliateservice.repositories.ProductMarkupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductMarkupService {

    @Autowired
    private ProductMarkupRepository pmRepository;

    public void save(ProductMarkupDto productMarkupDto){
        var productMarkup = ProductMarkupAdapter.getProductMarkup(productMarkupDto);
        pmRepository.save(productMarkup);
    }

    public ProductMarkupDto findByProductLinkId(String productLinkId){
        var productMarkup = pmRepository.findByProductLinkId(productLinkId).orElseThrow(
                () -> new ResourceNotFoundException("item " + productLinkId + " not found")
        );
        return ProductMarkupAdapter.getProductMarkupDTO(productMarkup);
    }


    public List<ProductMarkupDto> findAll() {
        return pmRepository.findAll().stream().map(p -> ProductMarkupAdapter.getProductMarkupDTO(p))
                .collect(Collectors.toList());
    }

    public ProductMarkupDto findById(String id) {
        var prodMarkup =  pmRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("item " + id + " not found")
                );

        return ProductMarkupAdapter.getProductMarkupDTO(prodMarkup);
    }

    public ProductMarkupDto update(String id, ProductMarkupDto productMarkup) {
        ProductMarkup prodMark = pmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("item not found for this id :: " + id));
        prodMark.setAmount(productMarkup.getAmount());
        prodMark.setMarkupType(productMarkup.getMarkupType());
        return ProductMarkupAdapter.getProductMarkupDTO(pmRepository.save(prodMark));
    }

    public void delete(String id) {
        ProductMarkup productMarkup = pmRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account " + id + " not found")
        );
        pmRepository.delete(productMarkup);
    }


}
