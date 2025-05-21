package com.example.magazzino.service;

import com.example.magazzino.mapper.CustomerMapper;
import com.example.magazzino.mapper.OrderMapper;
import com.example.magazzino.model.OrderStatus;
import com.example.magazzino.model.dto.OrderDto;
import com.example.magazzino.model.dto.ProductDto;
import com.example.magazzino.model.dto.QuantityDto;
import com.example.magazzino.model.entity.CustomerEntity;
import com.example.magazzino.model.entity.OrderEntity;
import com.example.magazzino.model.entity.ProductEntity;
import com.example.magazzino.model.entity.QuantityEntity;
import com.example.magazzino.repository.CustomerRepository;
import com.example.magazzino.repository.OrderRepository;
import com.example.magazzino.repository.ProductRepository;
import com.example.magazzino.repository.QuantityRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;


    public OrderService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;

        this.orderRepository = orderRepository;

    }

    @Transactional
    public OrderDto create(OrderDto orderDto) {
        // Creiamo l'ordine partendo dal DTO
        OrderEntity order = OrderMapper.mapToOrderEntity(orderDto);

        // Controlliamo se l'ordine esiste già
        Optional<OrderEntity> orderEntityOptional = orderRepository.searchByOrderName(order.getOrderName());
        if (orderEntityOptional.isPresent()) {
            throw new IllegalStateException("Order already exists");
        }
        // Prendiamo il cliente associato all'ordine
        CustomerEntity customer = customerRepository.findById(orderDto.getCustomer().getIdCustomer())
                .orElseThrow(() -> new IllegalStateException("Customer with ID "
                        + orderDto.getCustomer().getIdCustomer() + " does not exist"));
        // Salviamo il cliente aggiornato
        customerRepository.save(customer);
        // Salviamo l'ordine
        orderRepository.save(order);
        return OrderMapper.mapToOrderDto(orderRepository.save(order));
    }

    public void update(Long idOrder, String orderName, Boolean status) {
        OrderEntity order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + idOrder + " does not exists")
                );
        if(status != null && !Objects.equals(order.getStatus(), status))   {
            order.setStatus(status);
        }

        if(orderName != null && !Objects.equals(order.getOrderName(), orderName))   {
            order.setOrderName(orderName);
        }
        orderRepository.save(order);
    }

    public void delete(Long idOrder) {
        boolean exists = orderRepository.existsById(idOrder);
        if(!exists) {
            throw new IllegalStateException("Order " + idOrder + " does not exists");
        }
        orderRepository.deleteById(idOrder);
    }

    public List<OrderDto> getAll() {
        return OrderMapper.mapToOrderDto(orderRepository.findAll());
    }

    public OrderEntity updateOrderStatus(Long orderId, OrderStatus status) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato"));
        order.setOrderStatus(status);
        return orderRepository.save(order);

    }

}
