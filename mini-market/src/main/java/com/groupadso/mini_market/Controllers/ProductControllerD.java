package com.groupadso.mini_market.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupadso.mini_market.DTO.RequestDTO.ProductsRequestDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.HttpGlobalResponse;
import com.groupadso.mini_market.DTO.ResponseDTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.ProductsResponseDTO;
import com.groupadso.mini_market.Service.ProductsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductControllerD {
    private final ProductsService productsService;

    @PostMapping
    public ResponseEntity <List<MessageResponseDTO>> createProduct (@RequestBody List<ProductsRequestDTO> request){
        List<MessageResponseDTO> response = new ArrayList<>();
        for (ProductsRequestDTO productRequest : request) {
            response.add(productsService.createProduct(productRequest));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
    }

    @GetMapping
    public ResponseEntity<HttpGlobalResponse<List<ProductsResponseDTO>>> listProduct(){
        HttpGlobalResponse<List<ProductsResponseDTO>> response = new HttpGlobalResponse<>();

        try {
            response = productsService.listProduct();
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (RuntimeException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponseDTO> getProductById(@PathVariable Long id){
        try {
            ProductsResponseDTO response = productsService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductsRequestDTO request){
        try {
            MessageResponseDTO response = productsService.updateProduct(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            MessageResponseDTO response = new MessageResponseDTO();
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@PathVariable Long id){
        try {
            MessageResponseDTO response = productsService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            MessageResponseDTO response = new MessageResponseDTO();
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

  
}

