package com.example.magazzino.service;

import com.example.magazzino.model.dto.QuantityDto;
import com.example.magazzino.model.entity.CustomerEntity;
import com.example.magazzino.model.entity.ProductEntity;
import com.example.magazzino.model.entity.QuantityEntity;
import com.example.magazzino.repository.CustomerRepository;
import com.example.magazzino.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class QuantityService {

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    public QuantityEntity mapDtoToEntity(QuantityDto quantityDto) {
        CustomerEntity customerEntity = customerRepository.findById(quantityDto.getIdCustomer())
                .orElseThrow(() -> new RuntimeException("Cliente non trovato"));

        ProductEntity productEntity = productRepository.findById(quantityDto.getIdProduct())
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        QuantityEntity quantityEntity = new QuantityEntity();
        quantityEntity.setQuantity(quantityDto.getQuantity());
        quantityEntity.setCustomerEntity(customerEntity);
        quantityEntity.setProductEntity(productEntity);

        return quantityEntity;
    }
}
