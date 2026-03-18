package com.groupadso.mini_market.entity;

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
@Table(name = "products")
@Data
public class Products {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idProduct")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "price")
    private double price;

    @Column (name = "cantidad")
    private int quantity;

    @Column (name = "barcode", unique =  true, nullable = false)
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    

}
