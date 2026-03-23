package com.groupadso.mini_market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupadso.mini_market.Entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

}
