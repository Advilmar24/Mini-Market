package com.groupadso.mini_market.Service;

import org.springframework.stereotype.Service;
import com.groupadso.mini_market.Repository.ProductRepository;
import com.groupadso.mini_market.Repository.ProveedorRepository;
import com.groupadso.mini_market.Repository.EntradaProveedorRepository;
import com.groupadso.mini_market.Entity.ProductEntity;
import com.groupadso.mini_market.Entity.ProveedorEntity;
import com.groupadso.mini_market.Entity.EntradaProveedorEntity;

@Service
public class WarehouseService {

    private final ProductRepository productRepository;
    private final ProveedorRepository proveedorRepository;
    private final EntradaProveedorRepository entradaProveedorRepository;

    public WarehouseService(ProductRepository productRepository,
                            ProveedorRepository proveedorRepository,
                            EntradaProveedorRepository entradaProveedorRepository) {
        this.productRepository = productRepository;
        this.proveedorRepository = proveedorRepository;
        this.entradaProveedorRepository = entradaProveedorRepository;
    }

    public boolean entradaAlmacen(Long idProduct, Long idProveedor, Integer cantidad) {
        try {
            ProductEntity producto = productRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            ProveedorEntity proveedor = proveedorRepository.findById(idProveedor)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

            EntradaProveedorEntity entrada = new EntradaProveedorEntity();
            entrada.setProducto(producto);
            entrada.setProveedor(proveedor);
            entrada.setCantidad(cantidad);
            entradaProveedorRepository.save(entrada);

            producto.setQuantity(producto.getQuantity() + cantidad);
            productRepository.save(producto);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}