// Jorge Mario
package com.groupadso.mini_market.DTO;

import lombok.Data;
import java.util.List;

@Data
public class SalesRequestDTO {
  private String date;

  private Double total;


  // Se añade obligatoriamente el empleado que hizo la venta
  private Long employeeId;

  // Lista de detalles de venta con producto y cantidad
  private List<SalesDetailRequestDTO> details;
}
