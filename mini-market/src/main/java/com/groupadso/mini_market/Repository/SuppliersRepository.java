package com.groupadso.mini_market.Repository;

import com.groupadso.mini_market.Entity.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface SuppliersRepository extends JpaRepository<SuppliersEntity, Long> {

    
}