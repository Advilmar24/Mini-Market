package com.groupadso.mini_market.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class EntradaAlmacenRequestDTO {
    @NotNull (message = "Debes escribir el id del producto")
    private Long idProduct;
    @NotNull (message = "Debes escribir el id del proveedor")
    private Long idProveedor;
    
    @NotBlank(message = "Debes escribir la cantidad")
    private Integer cantidad;

}