package com.groupadso.mini_market.dto.ResponseDTO;

import lombok.Data;

@Data
public class ProductsResponseDTO {
    private Long id;

    private String name;

    private double price;
    
    private int quantity;

    private String barcode;

}
