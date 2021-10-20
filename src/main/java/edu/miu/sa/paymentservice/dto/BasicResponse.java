package edu.miu.sa.paymentservice.dtos;

public class BasicResponse {
    private Boolean isSuccessful;
    private String responseCode;
    private String responseDescription;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }


    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public BasicResponse(Boolean success)
    {
        isSuccessful = success;
    }
}



