package miu.sa.accountservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.sa.accountservice.model.Address;
import miu.sa.accountservice.model.PaymentMethod;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String role;
    private boolean isActive;
    private List<Address> addresses;
    private List<PaymentMethod> payments;
}
