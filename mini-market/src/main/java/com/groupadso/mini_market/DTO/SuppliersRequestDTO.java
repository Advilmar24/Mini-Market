package com.groupadso.mini_market.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SuppliersRequestDTO {

    @NotBlank(message = "Debes escribir el NOMBRE")
    private String nombre;

    @NotBlank(message = "Debes escribir el INT")
    private String INT;

    @NotBlank(message = "Debes escribir el TELEFONO")
    private String phone;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank(message = "Debes escribir la DIRECCIÓN")
    private String addres; 


}
