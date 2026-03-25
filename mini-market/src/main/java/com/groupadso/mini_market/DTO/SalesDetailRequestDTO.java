package com.groupadso.mini_market.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesDetailRequestDTO {
    // ID del producto que se desea vender
    @NotNull(message = "El idProduct es obligatorio")
    private Long idProduct;
    
    // Cantidad del producto que se va a vender
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
}
