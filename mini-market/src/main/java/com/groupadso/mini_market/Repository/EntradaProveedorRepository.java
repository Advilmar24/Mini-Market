package com.groupadso.mini_market.Repository;

import com.groupadso.mini_market.Entity.EntradaProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaProveedorRepository extends JpaRepository<EntradaProveedorEntity, Long> {}
