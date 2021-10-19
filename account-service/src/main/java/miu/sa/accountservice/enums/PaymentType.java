package miu.sa.accountservice.enums;

public enum PaymentType {
    CC(1,"CREDIT CARD"),
    BANK(2, "BANK"),
    PAYPAL(3,"PAYPAL");

    private int type;
    private String value;

    private PaymentType(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
