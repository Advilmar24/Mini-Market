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
@Table(name = "sales")
@Data
public class Sales {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private String date;

  @Column(name = "total")
  private Double total;

  // Relación obligatoria con Empleado (Quien realiza la transacción)
  @ManyToOne(optional = false)
  @JoinColumn(name = "employee_id", nullable = false)
  private Personal employee;

}
