package com.groupadso.mini_market.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Entity
@Table(name = "category")
@Data
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Long idCategory;

    @Column(name = "name")
    @NotNull(message = "El nombre de la categoría es obligatorio")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

}
