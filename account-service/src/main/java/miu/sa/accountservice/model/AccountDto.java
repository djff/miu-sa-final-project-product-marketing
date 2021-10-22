package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean isActive;
    private List<Address> addresses;
    private List<PaymentMethod> payments;
}
