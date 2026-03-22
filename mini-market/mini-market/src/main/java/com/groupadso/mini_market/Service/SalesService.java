package com.groupadso.mini_market.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupadso.mini_market.DTO.HttpGlobalResponse;
import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.SalesRequestDTO;
import com.groupadso.mini_market.DTO.SalesResponseDTO;
import com.groupadso.mini_market.DTO.SalesDetailRequestDTO;
import com.groupadso.mini_market.Entity.Personal;
import com.groupadso.mini_market.Entity.Product;
import com.groupadso.mini_market.Entity.Sales;
import com.groupadso.mini_market.Entity.SalesDetail;
import com.groupadso.mini_market.Exception.InsufficientStockException;
import com.groupadso.mini_market.Repository.PersonalRepository;
import com.groupadso.mini_market.Repository.ProductRepository;
import com.groupadso.mini_market.Repository.SalesDetailRepository;
import com.groupadso.mini_market.Repository.SalesRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SalesService {
  private final SalesRepository salesRepository;
  private final PersonalRepository PersonalRepository;
  private final ProductRepository productRepository;
  private final SalesDetailRepository salesDetailRepository;

  // @Transactional asegura que si ocurre un error (Regla 2), todas las operaciones
  // realizadas en base de datos (como la resta de stock) se reviertan.
  @Transactional
  public MessageResponseDTO createSales(SalesRequestDTO request) {
    MessageResponseDTO response = new MessageResponseDTO();
    Sales sales = new Sales();
    sales.setDate(request.getDate());
    
    // Relación Obligatoria: Validar Empleado
    if (request.getEmployeeId() == null) {
      throw new IllegalArgumentException("El ID del empleado es obligatorio para realizar una venta.");
    }
    
    Personal personal = PersonalRepository.findById(request.getEmployeeId())
        .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + request.getEmployeeId()));
    
    // Vincular la venta al empleado
    sales.setEmployee(personal);
    // Establecer total inicial temporal
    sales.setTotal(0.0);
    
    // Guardar la cabecera primero para poder asignarla a los detalles
    sales = salesRepository.save(sales);
    
    double totalCalculado = 0.0;
    
    if (request.getDetails() != null && !request.getDetails().isEmpty()) {
      for (SalesDetailRequestDTO detailDto : request.getDetails()) {
        Product product = productRepository.findById(detailDto.getIdProduct())
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + detailDto.getIdProduct()));
        
        // Regla de Negocio 2: Si no tiene stock suficiente, retorna error y cancela transacción
        if (product.getStock() < detailDto.getCantidad()) {
          throw new InsufficientStockException("Stock insuficiente para el producto: " + product.getName() + ". Stock disponible: " + product.getStock());
        }
        
        // Regla de Negocio 1: Restar automáticamente la cantidad vendida del stock
        product.setStock(product.getStock() - detailDto.getCantidad());
        productRepository.save(product);
        
        // Guardar el Detalle de Venta
        SalesDetail detail = new SalesDetail();
        detail.setSale(sales);
        detail.setProduct(product);
        detail.setQuantity(detailDto.getCantidad());
        detail.setSubtotal(product.getPrice() * detailDto.getCantidad());
        
        salesDetailRepository.save(detail); // Guardamos el detalle en BD
        
        totalCalculado += detail.getSubtotal();
      }
    }
    
    // Actualizamos el total de la cabecera con el cálculo real
    sales.setTotal(totalCalculado);
    salesRepository.save(sales);

    response.setMessage("Factura creada correctamente y stock descontado.");
    return response;
  }

  public HttpGlobalResponse<List<SalesResponseDTO>> listSales() {
    List<SalesResponseDTO> response = new ArrayList<>();
    HttpGlobalResponse<List<SalesResponseDTO>> responseSales = new HttpGlobalResponse<>();
    List<Sales> salesList = salesRepository.findAll();

    if (salesList.isEmpty()) {
      throw new RuntimeException("No hay facturas para mostrar.");
    } 

    for (Sales sold : salesList) {
      SalesResponseDTO show = new SalesResponseDTO();
      show.setId(sold.getId());
      show.setDate(sold.getDate());
      show.setTotal(sold.getTotal());
      response.add(show);
    }

    responseSales.setMessage("Facturas encontradas.");
    responseSales.setData(response);

    return responseSales;
  }

  public MessageResponseDTO deleteSale(long id) {
    if(!salesRepository.existsById(id)) {
      throw new RuntimeException("Venta no encontrada");
    }
    salesRepository.deleteById(id);
    return new MessageResponseDTO("Venta eliminada exitosamente");
  }

  public MessageResponseDTO updateSale(Long id, SalesRequestDTO request) {
    Sales sale = salesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Sale not found"));

    sale.setDate(request.getDate());
    sale.setTotal(request.getTotal());

    salesRepository.save(sale);

    return new MessageResponseDTO("Venta Actualizada exitosamente");
  }
}

