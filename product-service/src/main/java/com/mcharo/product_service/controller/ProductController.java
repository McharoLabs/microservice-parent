package com.mcharo.product_service.controller;

import com.mcharo.product_service.dto.ProductRequest;
import com.mcharo.product_service.dto.ProductResponse;
import com.mcharo.product_service.dto.ResponseDTO;
import com.mcharo.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse<Object>> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.createProduct(productRequest);

            ProductResponse<Object> response = new ProductResponse<>(
                    "success",
                    "Product created successfully.",
                    null
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateKeyException e) {
            ProductResponse<Object> response = new ProductResponse<>(
                    "error",
                    "Duplicate product name: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ProductResponse<Object> response = new ProductResponse<>(
                    "error",
                    "An error occurred while creating the product: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ProductResponse<List<ResponseDTO>>> getAllProducts() {
        try {
            List<ResponseDTO> products = productService.getAllProducts();
            ProductResponse<List<ResponseDTO>> response = new ProductResponse<>("success", "Products retrieved successful", products);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ProductResponse<List<ResponseDTO>> errorResponse = new ProductResponse<>("error", "An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
