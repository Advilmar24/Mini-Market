package com.groupadso.mini_market.Controllers;

import com.groupadso.mini_market.Entity.ProveedorEntity;
import com.groupadso.mini_market.Repository.ProveedorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @PostMapping
    public ResponseEntity<?> createProveedor(@Valid @RequestBody ProveedorEntity proveedor) {
        return ResponseEntity.ok(proveedorRepository.save(proveedor));
    }

    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> getAllProveedores() {
        return ResponseEntity.ok(proveedorRepository.findAll());
    }

    @GetMapping("/{id}")
public ResponseEntity<?> getProveedor(@PathVariable Long id) {
    return proveedorRepository.findById(id)
        .map(proveedor -> ResponseEntity.ok(proveedor))
        .orElseGet(() -> ResponseEntity.badRequest().body("Proveedor no encontrado"));
}

    @@PutMapping("/{id}")
public ResponseEntity<?> updateProveedor(@PathVariable Long id, @Valid @RequestBody ProveedorEntity proveedor) {
    return proveedorRepository.findById(id)
            .map(existing -> {
                existing.setName(proveedor.getName());
                existing.setNit(proveedor.getNit());
                existing.setPhone(proveedor.getPhone());
                existing.setEmail(proveedor.getEmail());
                existing.setAddress(proveedor.getAddress());
                ProveedorEntity updated = proveedorRepository.save(existing);
                return ResponseEntity.ok(updated);
            })
            .orElseGet(() -> ResponseEntity.badRequest().body("Proveedor no encontrado"));
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        }
        return ResponseEntity.badRequest().body("Proveedor no encontrado");
    }
}