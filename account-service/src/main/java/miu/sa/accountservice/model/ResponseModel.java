package miu.sa.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel implements Serializable {
    private String message;
    private boolean success;
    private Object data;
}
