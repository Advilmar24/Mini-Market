package com.groupadso.mini_market.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ventas")
@Data
public class SalesEntity {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idVenta")
  private Long id;

  @Column(name = "dateHire")
  private String date;

  @Column(name = "total")
  private Double total;

  // Relación obligatoria con Empleado (Quien realiza la transacción)
  @ManyToOne(optional = false)
  @JoinColumn(name = "idEmpleado", nullable = false)
  private StaffEntity employee;

}
