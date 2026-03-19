package com.groupadso.mini_market.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private final JdbcTemplate jdbcTemplate;

    public WarehouseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean entradaAlmacen(Long idProduct, Long idProveedor, Integer cantidad) {
        try {
            // Verificar si el producto existe y obtener el stock actual
            Integer stockActual = jdbcTemplate.queryForObject(
                "SELECT cantidad FROM products WHERE idProduct = ?",
                Integer.class,
                idProduct
            );

            if (stockActual == null) {
                return false; // Producto no encontrado
            }

            jdbcTemplate.update(
            "INSERT INTO entradas_proveedor (idProduct, idProveedor, cantidad) VALUES (?,?,?)",
            idProduct, idProveedor, cantidad
        );



            // Sumar la cantidad al stock actual
            Integer nuevoStock = stockActual + cantidad;

            // Actualizar el stock en la base de datos
            int filasAfectadas = jdbcTemplate.update(
                "UPDATE products SET cantidad = ? WHERE idProduct = ?",
                nuevoStock,
                idProduct
            );

            return filasAfectadas > 0;
        } catch (Exception e) {
            return false; // Error en la operación
        }
    }
}
