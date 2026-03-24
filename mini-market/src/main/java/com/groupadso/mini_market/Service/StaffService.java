package com.groupadso.mini_market.Service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import com.groupadso.mini_market.Repository.StaffRepository;
import com.groupadso.mini_market.Entity.StaffEntity;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public StaffEntity createStaff(StaffEntity staff) {
        return staffRepository.save(staff);
    }

    public StaffEntity updateStaff(Long idEmpleado, StaffEntity staffRequest) {
        staffRequest.setIdEmpleado(idEmpleado);
        return staffRepository.save(staffRequest);
    }

    public List<StaffEntity> getStaff() {
        return staffRepository.findAll();
    }

    public StaffEntity getStaff(Long idEmpleado) {
        return staffRepository.findById(idEmpleado).orElse(null);
    }

    public void deleteStaff(Long idEmpleado) {
        staffRepository.deleteById(idEmpleado);
    }

    public List<StaffEntity> listarStaff(String charge, LocalDate startDate, LocalDate endDate) {
        if (charge != null && !charge.isEmpty() && startDate != null && endDate != null) {
            return staffRepository.findByChargeAndHireDateBetween(charge, startDate, endDate);
        } else if (charge != null && !charge.isEmpty()) {
            return staffRepository.findByCharge(charge);
        } else if (startDate != null && endDate != null) {
            return staffRepository.findByHireDateBetween(startDate, endDate);
        }
        return staffRepository.findAll();
    }
}



    

