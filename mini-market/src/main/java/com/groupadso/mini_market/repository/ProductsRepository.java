package com.groupadso.mini_market.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupadso.mini_market.entity.Category;
import com.groupadso.mini_market.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository <Products, Long> {

    Optional<Products> findByBarcode(String barcode);

    boolean existsByBarcode(String barcode);

    boolean existsByNameIgnoreCaseAndCategory(String name, Category category);

    List<Products> findByCategory(Category category);
}
