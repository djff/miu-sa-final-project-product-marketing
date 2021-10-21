package com.miusaatega.affiliateservice.model.adapter;

import com.miusaatega.affiliateservice.model.entity.ProductMarkup;
import com.miusaatega.affiliateservice.model.ProductMarkupDto;

public class ProductMarkupAdapter {
    public static ProductMarkupDto getProductMarkupDTO(ProductMarkup productMarkup) {
        if(productMarkup == null) return null;
        var productMarkupDto = new ProductMarkupDto(productMarkup.getId(),
                productMarkup.getProductId(), productMarkup.getAffiliateId(), productMarkup.getProductLinkId(),
                productMarkup.getAmount(), productMarkup.getMarkupType(), productMarkup.isActive());
        return productMarkupDto;
    }

    public static ProductMarkup getProductMarkup(ProductMarkupDto productMarkupDTO) {
        if(productMarkupDTO == null) return null;
        var productMarkup = new ProductMarkup(productMarkupDTO.getId(),
                productMarkupDTO.getProductId(), productMarkupDTO.getAffiliateId(),productMarkupDTO.getProductLinkId(),
                productMarkupDTO.getAmount(), productMarkupDTO.getMarkupType(), productMarkupDTO.isActive());;
        return productMarkup;
    }
}
