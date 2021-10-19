package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    //    private UUID id;
    private String street;
    private String zip;
    private String city;
    private String state;
    private boolean isDefault;
}
