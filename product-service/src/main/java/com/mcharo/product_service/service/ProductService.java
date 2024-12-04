package com.mcharo.product_service.service;

import com.mcharo.product_service.dto.ProductRequest;
import com.mcharo.product_service.dto.ResponseDTO;
import com.mcharo.product_service.model.Product;
import com.mcharo.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        try {
            if (productRepository.existsByName(productRequest.getName())) {
                throw new DuplicateKeyException("Product with name '" + productRequest.getName() + "' already exists.");
            }

            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            productRepository.save(product);
            log.info("Product is saved: {}", product);

        } catch (DuplicateKeyException e) {
            log.error("Duplicate product name error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while creating product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create product: " + e.getMessage(), e);
        }
    }

    public List<ResponseDTO> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            return products.stream().map(this::mapToProductResponseDTO).toList();
        } catch (Exception e) {
            log.error("Error while retrieving all products: {}", e.getMessage(), e);
            throw  new RuntimeException("Failed to retrieve all products: " + e.getMessage(), e);
        }
    }

    private ResponseDTO mapToProductResponseDTO(Product product) {
        return ResponseDTO
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
