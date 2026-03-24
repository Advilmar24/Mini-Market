package com.groupadso.mini_market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupadso.mini_market.Entity.SalesDetailEntity;

@Repository
public interface SalesDetailRepository extends JpaRepository<SalesDetailEntity, Long> {
}
