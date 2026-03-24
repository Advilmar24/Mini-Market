package com.groupadso.mini_market.Controllers;

import com.groupadso.mini_market.Entity.ProductEntity;

import com.groupadso.mini_market.Repository.ProductRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductEntity product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
public ResponseEntity<?> getProduct(@PathVariable Long id) {
    Optional<ProductEntity> optional = productRepository.findById(id);

    if (optional.isPresent()) {
        return ResponseEntity.ok(optional.get());
    } else {
        return ResponseEntity.badRequest().body("Producto no encontrado");
    }
}

    // UPDATE
    @PutMapping("/{id}")
public ResponseEntity<?> updateProduct(@PathVariable Long idProduct, @Valid @RequestBody ProductEntity product) {
    Optional<ProductEntity> optional = productRepository.findById(idProduct);

    if (optional.isPresent()) {
        ProductEntity existing = optional.get();
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());
        existing.setProveedor(product.getProveedor());
        existing.setIdCategory(product.getIdCategory());
        ProductEntity updated = productRepository.save(existing);
        return ResponseEntity.ok(updated);
    } else {
        return ResponseEntity.badRequest().body("Producto no encontrado");
    }
}

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        }
        return ResponseEntity.badRequest().body("Producto no encontrado");
    }
}