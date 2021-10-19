package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardPayment implements Serializable {
    private String cardNumber;
    private String nameOnCard;
    private String expDate;
}
