package com.groupadso.mini_market.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
//DTO lo que recibe del cliente al crear al personal
public class StaffRequestDTO {
    // Esta anotacion me sirve para no dejar espacios vacios @NotBlank
    @NotBlank(message = "La cedula es OBLIGATORIA")
    private String idCard;

    @NotBlank(message = "Debes escribir el NOMBRE")
    private String name;

    @NotBlank (message = "El cargo de ser OBLOGATORIO")
    // esta anotacion  @Pattern sive paraque cuando yo ingrese un dato sea alguna de las opciones esperadas, osea que cumpla
    // con una EXPRESION REGULAR o REGEX ahora (^) este inicia la cadena y ($) este la cierra o es el finde la cadena
    @Pattern(regexp = "^(CAJERO|ADMINISTRADOR|AUXILIAR)$", 
    message = "Los cargos admitidos son CAJERO, ADMINISTRADOR y AUXILIAR")
    private String charge; 

    //esta anotacion NotNull me permite que ese campo no quede Null, y no se pone la anotacion @NotBlack
    //Por que esta es para texto, en cambio este sirve para fechas y numeros.
    @NotNull(message = "la fecha es OBLIGATORIA")
    // la clase LocalDate me permite anotar la fecha sin necesidad de horas.
    private LocalDate hireDate;

    @NotNull(message = "el Salario es OBLIGATORIO")
    private Double salary;



    
    
}
