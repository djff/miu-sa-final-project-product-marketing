package edu.miu.inventoryservice.miusamsproductatega.controller;

import edu.miu.inventoryservice.miusamsproductatega.dto.ProductOrder;
import lombok.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManyProduct {
    private List<ProductOrder> productOrders;
}
