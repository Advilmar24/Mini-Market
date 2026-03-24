package com.groupadso.mini_market.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupadso.mini_market.DTO.RequestDTO.CategoryRequestDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.CategoryResponseDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.HttpGlobalResponse;
import com.groupadso.mini_market.DTO.ResponseDTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.ProductsResponseDTO;
import com.groupadso.mini_market.Service.ProductsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final ProductsService productsService;

    @PostMapping
    public ResponseEntity<HttpGlobalResponse<MessageResponseDTO>> createCategory(@Valid @RequestBody CategoryRequestDTO request) {
        HttpGlobalResponse<MessageResponseDTO> response = new HttpGlobalResponse<>();
        MessageResponseDTO data = productsService.createCategory(request);
        response.setData(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpGlobalResponse<CategoryResponseDTO>> getCategory(@PathVariable Long id) {
        HttpGlobalResponse<CategoryResponseDTO> response = new HttpGlobalResponse<>();
        CategoryResponseDTO data = productsService.getCategoryByIdCResponseDTO(id);
        response.setMessage("Categoría encontrada");
        response.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<HttpGlobalResponse<List<ProductsResponseDTO>>> getProductsByCategory(@PathVariable Long categoryId) {
        HttpGlobalResponse<List<ProductsResponseDTO>> response = productsService.getProductsByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
