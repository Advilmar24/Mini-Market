package com.groupadso.mini_market.dto.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    
    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    private String name;
}
