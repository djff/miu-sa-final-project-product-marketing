package edu.miu.sa.paymentservice.dtos;

public class Payment {
    private String type;
    private MetaData metaData;
    private Boolean isDefault;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}


