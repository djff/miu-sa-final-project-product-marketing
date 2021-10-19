package edu.miu.model;

public class ResponseFormat {
    private String message;
    private Boolean status;
    private Object data;

    public ResponseFormat() {
    }


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseFormat{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }


    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
