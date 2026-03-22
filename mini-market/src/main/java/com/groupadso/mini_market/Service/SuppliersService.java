package com.groupadso.mini_market.Service;

import com.groupadso.mini_market.DTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.SuppliersRequestDTO;
import com.groupadso.mini_market.DTO.SuppliersResponseDTO;
import com.groupadso.mini_market.Entity.ProveedorEntity;
import com.groupadso.mini_market.Repository.SuppliersRepository;
import com.groupadso.mini_market.Constants.MessageConstants;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuppliersService {

    private final SuppliersRepository suppliersRepository;

    public SuppliersService(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    public SuppliersResponseDTO createSupplier(SuppliersRequestDTO dto) {
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setName(dto.getName());
        proveedor.setNit(dto.getNit());
        proveedor.setPhone(dto.getPhone());
        proveedor.setMail(dto.getMail());
        proveedor.setAddress(dto.getAddress());

        ProveedorEntity saved = suppliersRepository.save(proveedor);

        return mapToResponse(saved);
    }

    public SuppliersResponseDTO updateSupplier(Long idProveedor, SuppliersRequestDTO dto) {
        ProveedorEntity proveedor = suppliersRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        proveedor.setName(dto.getName());
        proveedor.setPhone(dto.getPhone());
        proveedor.setMail(dto.getMail());
        proveedor.setAddress(dto.getAddress());

        ProveedorEntity updated = suppliersRepository.save(proveedor);

        return mapToResponse(updated);
    }

    public List<SuppliersResponseDTO> getSuppliers() {
        return suppliersRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SuppliersResponseDTO getSupplier(Long idProveedor) {
        ProveedorEntity proveedor = suppliersRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return mapToResponse(proveedor);
    }

    public MessageResponseDTO deleteSupplier(Long idProveedor) {
        suppliersRepository.deleteById(idProveedor);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage(MessageConstants.MESSAGE_RESPONSE_DELETE_USER);
        return response;
    }

    private SuppliersResponseDTO mapToResponse(ProveedorEntity proveedor) {
        SuppliersResponseDTO dto = new SuppliersResponseDTO();
        dto.setIdProveedor(proveedor.getIdProveedor());
        dto.setName(proveedor.getName());
        dto.setNit(proveedor.getNit());
        dto.setPhone(proveedor.getPhone());
        dto.setMail(proveedor.getMail());
        dto.setAddress(proveedor.getAddress());
        return dto;
    }
}