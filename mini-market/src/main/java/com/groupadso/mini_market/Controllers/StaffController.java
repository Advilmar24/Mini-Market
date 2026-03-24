package com.groupadso.mini_market.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.groupadso.mini_market.Entity.StaffEntity;
import com.groupadso.mini_market.Service.StaffService;
import com.groupadso.mini_market.Constants.MessageConstanst;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<String> createStaff(@RequestBody @Valid StaffEntity staff) {
        staffService.createStaff(staff);
        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(MessageConstanst.MESSAGE_RESPONSE_CREATE_USER);
    }

    @PutMapping("/{idEmpleado}")
    public ResponseEntity<String> updateStaff(@PathVariable Long idEmpleado,
                                            @RequestBody @Valid StaffEntity staff) {
        staffService.updateStaff(idEmpleado, staff);
        return ResponseEntity.ok(MessageConstanst.MESSAGE_RESPONSE_UPDATE_USER);
    }

    @GetMapping
    public ResponseEntity<List<StaffEntity>> getStaff() {
        return ResponseEntity.ok(staffService.getStaff());
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<StaffEntity> getStaff(@PathVariable Long idEmpleado) {
        return ResponseEntity.ok(staffService.getStaff(idEmpleado));
    }

    @DeleteMapping("/{idEmpleado}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long idEmpleado) {
        staffService.deleteStaff(idEmpleado);
        return ResponseEntity.ok(MessageConstanst.MESSAGE_RESPONSE_DELETE_USER);
    }
}
