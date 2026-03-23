package com.groupadso.mini_market.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.groupadso.mini_market.DTO.RequestDTO.CategoryRequestDTO;
import com.groupadso.mini_market.DTO.RequestDTO.ProductsRequestDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.CategoryResponseDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.HttpGlobalResponse;
import com.groupadso.mini_market.DTO.ResponseDTO.MessageResponseDTO;
import com.groupadso.mini_market.DTO.ResponseDTO.ProductsResponseDTO;
import com.groupadso.mini_market.Entity.Category;
import com.groupadso.mini_market.Entity.Product;
import com.groupadso.mini_market.Repository.CategoryRepository;
import com.groupadso.mini_market.Repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    public MessageResponseDTO createProduct(ProductsRequestDTO request){
        MessageResponseDTO response = new MessageResponseDTO();

        // Validar que la categoría exista
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + request.getCategoryId()));

                if (productsRepository.existsByNameIgnoreCaseAndCategory(request.getName().trim(), category)) {
                    throw new RuntimeException("Ya existe un producto con el mismo nombre en esta categoría");
                    
                }

        Product products = new Product();
        products.setName(request.getName());
        products.setPrice(request.getPrice());
        products.setQuantity(request.getQuantity());
        products.setCategory(category);

        String barcode = generatedBarcode();

        products.setBarcode(barcode);

        try {
             productsRepository.save(products);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error, codigo de barra duplicado");
        }
        
        response.setMessage("Producto creado correctamente. Barcode " + barcode);
        return response;
    }

    private String generatedBarcode(){
        return UUID.randomUUID().toString();
    }

    public HttpGlobalResponse<List<ProductsResponseDTO>> listProduct(){
        List<ProductsResponseDTO> response = new ArrayList<>();
        HttpGlobalResponse<List<ProductsResponseDTO>> responseProduct = new HttpGlobalResponse<>();
        List<Product> products = productsRepository.findAll();

        if (products.isEmpty()) {
            throw new RuntimeException("No hay productos para mostrar");
        }

        for(Product product : products){
            ProductsResponseDTO item = new ProductsResponseDTO();

            item.setId(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(product.getQuantity());
            item.setBarcode(product.getBarcode());
            response.add(item);
        }

        responseProduct.setMessage("Productos encontrados.");
        responseProduct.setData(response);

        return responseProduct;
    }

    public ProductsResponseDTO getProductById(Long id){
        Product product = productsRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));

        ProductsResponseDTO response = new ProductsResponseDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setBarcode(product.getBarcode());
        return response;
    }

    public MessageResponseDTO updateProduct(Long id, ProductsRequestDTO request){
        Product product = productsRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        productsRepository.save(product);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Producto actualizado correctamente");
        
        return response;
    }

    public MessageResponseDTO deleteProduct(Long id){
        Product product = productsRepository.findByIdAndStatusTrue(id).orElseThrow(()-> new RuntimeException("Producto no encontrado."));

        product.setStatus(false); //BORRADO LOGICO
        product.setDeletedAt(LocalDateTime.now());   

        productsRepository.save(product);

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Producto eliminado correctamente (Borrado lógico)");

        return response;
    }

    public CategoryResponseDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Categoría no encontrada"));

        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(category.getId());
        response.setName(category.getName());

        List<ProductsResponseDTO> productsDTO =new ArrayList<>();

        for (Product products : category.getProducts()){
            ProductsResponseDTO dto = new ProductsResponseDTO();
            dto.setId(products.getId());
            dto.setName(products.getName());
            dto.setPrice(products.getPrice());
            dto.setQuantity(products.getQuantity());
            dto.setBarcode(products.getBarcode());

            productsDTO.add(dto);
        }
        response.setProducts(productsDTO);
        return response;
    }

    public HttpGlobalResponse<List<ProductsResponseDTO>> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + categoryId));

        List<Product> productsList = productsRepository.findByCategory(category);

        if (productsList.isEmpty()) {
            throw new RuntimeException("No hay productos en esta categoría");
        }

        List<ProductsResponseDTO> response = new ArrayList<>();
        for (Product product : productsList) {
            ProductsResponseDTO dto = new ProductsResponseDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setQuantity(product.getQuantity());
            dto.setBarcode(product.getBarcode());
            response.add(dto);
        }

        HttpGlobalResponse<List<ProductsResponseDTO>> result = new HttpGlobalResponse<>();
        result.setMessage("Productos de la categoría: " + category.getName());
        result.setData(response);
        return result;
    }

    public MessageResponseDTO createCategory(CategoryRequestDTO request) {
        // Verificar si la categoría ya existe
        // Nota: Se puede mejorar agregando un método findByName en CategoryRepository

        Category category = new Category();
        category.setName(request.getName());

        try {
            categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al crear la categoría");
        }

        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Categoría creada correctamente: " + request.getName());
        return response;
    }
}
