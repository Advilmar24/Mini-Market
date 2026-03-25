// Jorge Mario
package com.groupadso.mini_market.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class SalesRequestDTO {
  @NotBlank(message = "La fecha es obligatoria")
  private String date;

  private Double total;


  // Se añade obligatoriamente el empleado que hizo la venta
  @NotNull(message = "El employeeId es obligatorio")
  private Long employeeId;

  // Lista de detalles de venta con producto y cantidad
  @NotEmpty(message = "La venta debe incluir al menos un detalle")
  @Valid
  private List<SalesDetailRequestDTO> details;
}
