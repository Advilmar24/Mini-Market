package com.groupadso.mini_market.DTO;

import lombok.Data;

@Data
public class EntradaAlmacenResponseDTO {
    private Long idProduct;
    private Long idProveedor;
    private Integer cantidad;
}
