package edu.miu.inventoryservice.miusamsproductatega.dto;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {
    private UUID productId;
    private int quantity;
}
//{
//        isDeductable: true (boolean),
//        productList: [
//        {
//        "productId": 1323434,
//        "quantity": 10 (int)
//        }
//        ]
//        }