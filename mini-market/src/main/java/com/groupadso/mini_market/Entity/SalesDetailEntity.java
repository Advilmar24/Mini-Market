package com.groupadso.mini_market.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "detalle_venta")
@Data
public class SalesDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalle")
    private Long id;

    // Relación Muchos a Uno con Sales (Venta Cabecera)
    @ManyToOne(optional = false)
    @JoinColumn(name = "idVenta", nullable = false)
    private SalesEntity sale;

    // Relación Muchos a Uno con Product (Para descontar stock)
    @ManyToOne(optional = false)
    @JoinColumn(name = "idProduct", nullable = false)
    private ProductEntity product;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;
}
