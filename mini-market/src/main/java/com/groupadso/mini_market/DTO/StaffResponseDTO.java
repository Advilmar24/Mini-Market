package com.groupadso.mini_market.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
// lo que devuelve al cliente despues de crear un usuario
public class StaffResponseDTO {

    private Long id;

    private String idCard;

    private String name;

    private String charge;

    private LocalDate hireDate;

    private Double salary;
    
}
