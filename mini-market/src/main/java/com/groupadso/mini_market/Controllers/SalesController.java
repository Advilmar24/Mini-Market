package com.groupadso.mini_market.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import com.groupadso.mini_market.DTO.HttpGlobalResponse;
import com.groupadso.mini_market.DTO.MessageResponseSalesDTO;
import com.groupadso.mini_market.DTO.SalesRequestDTO;
import com.groupadso.mini_market.DTO.SalesResponseDTO;
import com.groupadso.mini_market.Service.SalesService;
import com.groupadso.mini_market.exception.InsufficientStockException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {
  

  private final SalesService salesService;


  @PostMapping
  public ResponseEntity<MessageResponseSalesDTO> createSales(@RequestBody SalesRequestDTO request) {
    // Ya no es necesario el try catch si usamos @ExceptionHandler, 
    // pero lo dejaremos para otro tipo de errores generales.
    // La excepción de Stock subirá y será cachada por handleInsufficientStock.
    MessageResponseSalesDTO response = salesService.createSales(request); 
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  
  @GetMapping
  public ResponseEntity<HttpGlobalResponse<List<SalesResponseDTO>>> listSales() {
    HttpGlobalResponse<List<SalesResponseDTO>> response = new HttpGlobalResponse<>();

    try {
      response = salesService.listSales();
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch(RuntimeException e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponseSalesDTO> deleteSale(@PathVariable Long id) {
    try {
      MessageResponseSalesDTO response = salesService.deleteSale(id);
      return ResponseEntity.status(HttpStatus.OK).body(response);
  
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseSalesDTO(e.getMessage()));

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponseSalesDTO("Error al eliminar la venta"));

    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<MessageResponseSalesDTO> updateSale(
    @PathVariable Long id,
    @RequestBody SalesRequestDTO request) {

  try {
    MessageResponseSalesDTO response = salesService.updateSale(id, request);
    return ResponseEntity.status(HttpStatus.OK).body(response);

  } catch (RuntimeException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseSalesDTO(e.getMessage()));

  } catch (Exception e) {
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new MessageResponseSalesDTO("Error al actualizar la venta"));
  }
  }

  // ============== MANEJO DE ERRORES CENTRALIZADO EN CONTROLADOR ===============
  // Regla 2: Retornar 400 Bad Request si no hay stock
  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity<MessageResponseSalesDTO> handleInsufficientStock(InsufficientStockException ex) {
      MessageResponseSalesDTO errorDto = new MessageResponseSalesDTO(ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }

  // Excepción extra si el ID de producto o empleado no existe (también retorna 400)
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<MessageResponseSalesDTO> handleIllegalArgument(IllegalArgumentException ex) {
      MessageResponseSalesDTO errorDto = new MessageResponseSalesDTO(ex.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }
}
