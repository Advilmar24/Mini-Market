package com.groupadso.mini_market.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "personal")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Long idEmpleado;

    @NotBlank(message = "El número de cédula no puede estar vacío")
    private String idCard;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    // Restricción: solo AUXILIAR, CAJERO o ADMINISTRADOR
    @NotBlank
    @Pattern(regexp = "AUXILIAR|CAJERO|ADMINISTRADOR",
            message = "El cargo debe ser AUXILIAR, CAJERO o ADMINISTRADOR")
    private String charge;

    @NotNull(message = "La fecha de contratación es obligatoria")
    private LocalDate hireDate;

    @Positive(message = "El salario debe ser mayor que cero")
    private Double salary;
}
