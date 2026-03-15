package com.groupadso.mini_market.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.StaffRequestDTO;
import com.groupadso.mini_market.DTO.StaffResponseDTO;
import com.groupadso.mini_market.Services.StaffService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/staff")
public class StaffController {
    
    private final StaffService staffService;

    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }

    @PostMapping()
    public ResponseEntity<StaffResponseDTO> createStaff(@RequestBody StaffRequestDTO staffRequestDTO){
        StaffResponseDTO response = staffService.createStaff(staffRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()

    public ResponseEntity<List<StaffResponseDTO>> getStaff(){
        List<StaffResponseDTO> response = staffService.getStaff();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{idEmpleado}")
    public ResponseEntity<StaffResponseDTO> getStaff(@PathVariable Long id){
        StaffResponseDTO response = staffService.getStaff(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);

    }

    @DeleteMapping("/{idEmpleado}")

    public ResponseEntity<MessageResponseDTO> deleteStaff(@PathVariable Long idEmpleado){
        MessageResponseDTO response = staffService.deleteStaff(idEmpleado);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
