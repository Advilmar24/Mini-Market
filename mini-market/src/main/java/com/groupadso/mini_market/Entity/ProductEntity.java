package com.groupadso.mini_market.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Long idProduct;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "barcode", unique = true, nullable = false)
    private String barcode;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

     @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = true)
    private ProveedorEntity proveedor;

    @ManyToOne
    @JoinColumn(name = "idCategory", nullable = true)
    private Category category;


}
