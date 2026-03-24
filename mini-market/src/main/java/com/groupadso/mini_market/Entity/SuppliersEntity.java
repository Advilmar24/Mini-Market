package com.groupadso.mini_market.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
@Table(name = "proveedores")
public class SuppliersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Long idProveedor;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El NIT es obligatorio")
    private String nit;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String mail;
    @NotBlank(message = "La dirección es obligatoria")
    private String address;

}
