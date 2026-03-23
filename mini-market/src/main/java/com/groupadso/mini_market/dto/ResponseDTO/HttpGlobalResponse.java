package com.groupadso.mini_market.dto.ResponseDTO;

import lombok.Data;

@Data
public class HttpGlobalResponse<T> {
    private T data;

    private String message;
    
}
