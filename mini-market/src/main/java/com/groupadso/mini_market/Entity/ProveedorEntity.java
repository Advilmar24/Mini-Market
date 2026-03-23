package com.groupadso.mini_market.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "proveedores")
@Data
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El NIT es obligatorio")
    private String nit;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    @Email(message = "El correo debe ser válido")
    private String mail;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;
}

