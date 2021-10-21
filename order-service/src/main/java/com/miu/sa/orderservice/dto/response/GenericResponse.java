package com.miu.sa.orderservice.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    public String message;
    public Boolean success;
    public Object data;
}
