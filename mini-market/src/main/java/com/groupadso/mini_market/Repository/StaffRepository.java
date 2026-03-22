package com.groupadso.mini_market.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.groupadso.mini_market.Entity.StaffEntity;
import java.time.LocalDate;
import java.util.List;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    List<StaffEntity> findByCharge(String charge);
    List<StaffEntity> findByHireDateBetween(LocalDate start, LocalDate end);
    List<StaffEntity> findByChargeAndHireDateBetween(String charge, LocalDate start, LocalDate end);
}
