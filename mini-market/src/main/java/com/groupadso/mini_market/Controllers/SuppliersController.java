package com.groupadso.mini_market.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersRequestDTO;
import com.groupadso.mini_market.DTO.SuppliersResponseDTO;
import com.groupadso.mini_market.Service.SuppliersService;
import com.groupadso.mini_market.DTO.SuppliersResponseDTO;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/suppliers")
public class SuppliersController {
    private final SuppliersService suppliersService;

    public SuppliersController(SuppliersService suppliersService){
        this.suppliersService = suppliersService;
    }

@PostMapping
// Se agrega @Valid para activar la validación de los objetos SuppliersRequestDTO
public ResponseEntity<List<SuppliersResponseDTO>> createSuppliers(@RequestBody @jakarta.validation.Valid List<SuppliersRequestDTO> suppliersList){
    List<SuppliersResponseDTO> responses = new ArrayList<>();
    for (SuppliersRequestDTO supplier : suppliersList){
        responses.add(suppliersService.createSupplier(supplier));
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(responses);
}


    @GetMapping()

    public ResponseEntity<List<SuppliersResponseDTO>> getSuppliers(){
        List<SuppliersResponseDTO> response = suppliersService.getSuppliers();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    
    @PutMapping("/{idProveedor}")
public ResponseEntity<SuppliersResponseDTO> updateSupplier(
        @PathVariable("idProveedor") Long idProveedor,
        @RequestBody @jakarta.validation.Valid SuppliersRequestDTO suppliersRequestDTO) {
    
    SuppliersResponseDTO response = suppliersService.updateSupplier(idProveedor, suppliersRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).body(response);
}

    @GetMapping("/{idProveedor}")
    public ResponseEntity<SuppliersResponseDTO> getSupplier(@PathVariable Long id){
        SuppliersResponseDTO response = suppliersService.getSupplier(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);

    }

    @DeleteMapping("/{idProveedor}")

    public ResponseEntity<MessageResponseDTO> deleteSupplier(@PathVariable Long idProveedor){
        MessageResponseDTO response = suppliersService.deleteSupplier(idProveedor);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
