package edu.miu.inventoryservice.miusamsproductatega.dto;


import edu.miu.inventoryservice.miusamsproductatega.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String vendor;
    private Long quantity;
    private Double amount;
    private String category;
    public Product toEntity() {
        return new Product( id, name, vendor, quantity, amount , category);
    }
    public static ProductDTO of(Product product) {return new ProductDTO( product.getProductId(), product.getName(), product.getVendor(),
                product.getQuantity(), product.getAmount(), product.getCategory());
    }
}

