package edu.miu.sa.paymentservice.dto;

public class BasicResponse {
    private Boolean successful;
    private String responseCode;
    private String responseDescription;
    private PaymentResponse paymentResponse;

    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(PaymentResponse paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

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
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        successful = successful;
    }

    public BasicResponse(Boolean success)
    {
        successful = success;
    }
}



