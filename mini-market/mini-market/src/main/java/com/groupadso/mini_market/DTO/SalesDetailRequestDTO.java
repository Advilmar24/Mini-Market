package com.groupadso.mini_market.DTO;

import lombok.Data;

@Data
public class SalesDetailRequestDTO {
    // ID del producto que se desea vender
    private Long idProduct;
    
    // Cantidad del producto que se va a vender
    private Integer cantidad;
}
