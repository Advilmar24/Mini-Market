package com.groupadso.mini_market.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupadso.mini_market.Entity.Category;
import com.groupadso.mini_market.Entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository <Product, Long> {

    List<Product> findByStatusTrue();
    Optional<Product> findByIdAndStatusTrue(Long id);  
    List<Product> findByCategoryAndStatusTrue(Category category);

    Optional<Product> findByBarcode(String barcode);

    boolean existsByBarcode(String barcode);

    boolean existsByNameIgnoreCaseAndCategory(String name, Category category);

    List<Product> findByCategory(Category category);
}
