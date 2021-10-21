package edu.miu.inventoryservice.miusamsproductatega.mongo.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String name;

}