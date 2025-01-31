package com.example.magazzino.service;

import com.example.magazzino.mapper.ProductMapper;
import com.example.magazzino.model.dto.ProductDto;
import com.example.magazzino.model.entity.OrderEntity;
import com.example.magazzino.model.entity.ProductEntity;
import com.example.magazzino.repository.OrderRepository;
import com.example.magazzino.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(ProductDto productDto) {
        ProductEntity product = ProductMapper.mapProductToEntity(productDto);

        Optional<ProductEntity> productEntityOptional = productRepository
                .searchByCategory(product.getCategory());
        if(productEntityOptional.isPresent()) {
            throw new IllegalStateException("ID already exists");
        }

        productRepository.save(product);
    }

    public ProductEntity getProduct(Long idProduct, Double price) {
        ProductEntity product = productRepository.findById(idProduct)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + idProduct + " does not exists")
                );
        return product;
    }

    public void update(Long idProduct, String description, String category, Integer quantity, Integer stock) {
        ProductEntity product = productRepository.findById(idProduct)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + idProduct + " does not exists")
                );
        if(description != null && !description.isEmpty() && !Objects.equals(product.getDescription(), description))   {
            product.setDescription(description);
        }

        if(category != null && !category.isEmpty() && !Objects.equals(product.getDescription(), category)) {
            Optional<ProductEntity> productEntity = productRepository.searchByCategory(category);
            if (productEntity.isPresent()) {
                throw new IllegalStateException("category exists");
            }
            product.setCategory(category);
        }

        if(product.getQuantity() != null && !Objects.equals(product.getQuantity(), quantity)) {
            product.setQuantity(quantity);
        }

        if(product.getStock() != null && !Objects.equals(product.getStock(), stock)) {
            product.setStock(stock);
        }
        productRepository.save(product);
    }

    public void delete(Long idProduct) {
        boolean exists = productRepository.existsById(idProduct);
        if(!exists) {
            throw new IllegalStateException("Product " + idProduct + " does not exists");
        }
        productRepository.deleteById(idProduct);
    }

    public List<ProductDto> getAll() {
        return ProductMapper.mapProductToDto(productRepository.findAll());
    }
}
