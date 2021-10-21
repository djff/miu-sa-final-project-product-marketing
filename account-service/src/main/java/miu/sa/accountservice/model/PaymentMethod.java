package miu.sa.accountservice.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.sa.accountservice.enums.PaymentType;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod implements Serializable {
//    private UUID id;
    private PaymentType type;
    private String metaData;
    private boolean isDefault;

    public Object getMetaData() {
        Gson gson=new Gson();
        PaymentType p = getType();
        switch (p) {
            case BANK:  return gson.fromJson(metaData, BankPayment.class);
            case CC: return gson.fromJson(metaData, CreditCardPayment.class);
            default: return metaData;
        }
    }

    public void setMetaData(Object metaData) {
        Gson gson=new Gson();
//        System.out.println("before set:: " + metaData);
        this.metaData = gson.toJson(metaData);
    }
}
