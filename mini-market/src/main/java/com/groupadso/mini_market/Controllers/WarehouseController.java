package com.groupadso.mini_market.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupadso.mini_market.DTO.EntradaAlmacenResponseDTO;
import com.groupadso.mini_market.Service.WarehouseService;



@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/entrada")
    public ResponseEntity<String> entradaAlmacen(@RequestBody EntradaAlmacenResponseDTO request) {
        boolean resultado = warehouseService.entradaAlmacen(request.getIdProduct(), request.getIdProveedor(), request.getCantidad());
        if (resultado) {
            return ResponseEntity.ok("Stock actualizado correctamente");
        } else {
            return ResponseEntity.badRequest().body("Error al actualizar el stock");
        }
    }
}


