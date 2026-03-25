package com.groupadso.mini_market.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupadso.mini_market.Entity.Category;
import com.groupadso.mini_market.Entity.ProductEntity;

@Repository
public interface ProductsRepository extends JpaRepository <ProductEntity, Long> {

    List<ProductEntity> findByStatusTrue();
    Optional<ProductEntity> findByIdProductAndStatusTrue(Long idProduct);  
    List<ProductEntity> findByCategoryAndStatusTrue(Category category);

    Optional<ProductEntity> findByBarcode(String barcode);

    boolean existsByBarcode(String barcode);

    boolean existsByNameIgnoreCaseAndCategory(String name, Category category);

    List<ProductEntity> findByCategory(Category category);
}
