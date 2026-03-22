package com.groupadso.mini_market.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.groupadso.mini_market.DTO.EntradaAlmacenRequestDTO;
import com.groupadso.mini_market.Service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/entrada")
    public ResponseEntity<String> entradaAlmacen(@RequestBody EntradaAlmacenRequestDTO request) {
        boolean resultado = warehouseService.entradaAlmacen(
                request.getIdProduct(),
                request.getIdProveedor(),
                request.getCantidad()
        );

        if (resultado) {
            return ResponseEntity.ok("Entrada registrada y stock actualizado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al registrar la entrada");
        }
    }
}

