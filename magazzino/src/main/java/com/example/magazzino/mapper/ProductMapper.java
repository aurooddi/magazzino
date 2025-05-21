package com.example.magazzino.mapper;

import com.example.magazzino.model.dto.ProductDto;
import com.example.magazzino.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductDto mapProductToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setIdProduct(productEntity.getId());
        productDto.setDescription(productEntity.getDescription());
        productDto.setCategory(productEntity.getCategory());
        productDto.setPrice(productEntity.getPrice());
        productDto.setStock(productEntity.getStock());
        return productDto;
    }

    public static ProductEntity mapProductToEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.getIdProduct());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setCategory(productDto.getCategory());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setStock(productDto.getStock());
        return productEntity;
    }

    public static List<ProductDto> mapProductToDto(List<ProductEntity> productEntity) {
        List<ProductDto> productDtoList = new ArrayList<>();

        for(ProductEntity product : productEntity) {

            ProductDto productDto = new ProductDto();
            productDto = mapProductToDto(product);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    public static List<ProductEntity> mapProductToEntity(List<ProductDto> productDto) {
        List<ProductEntity> productEntityList = new ArrayList<>();

        for(ProductDto product : productDto) {

            ProductEntity productEntity = new ProductEntity();
            productEntity = mapProductToEntity(product);
            productEntityList.add(productEntity);
        }
        return productEntityList;
    }

}
