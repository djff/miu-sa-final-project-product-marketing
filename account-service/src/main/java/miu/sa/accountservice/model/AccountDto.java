package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private boolean isActive;
    private List<Address> addresses =  new ArrayList<>();
    private List<PaymentMethod> payments = new ArrayList<>();
}
