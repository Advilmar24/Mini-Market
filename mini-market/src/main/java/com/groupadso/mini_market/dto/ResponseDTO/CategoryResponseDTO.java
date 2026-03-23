package com.groupadso.mini_market.DTO.ResponseDTO;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private long id;
    private String name; 
    private List<ProductsResponseDTO> products;
}
