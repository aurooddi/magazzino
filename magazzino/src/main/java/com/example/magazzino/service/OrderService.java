package com.example.magazzino.service;

import com.example.magazzino.mapper.OrderMapper;
import com.example.magazzino.model.dto.OrderDto;
import com.example.magazzino.model.dto.ProductDto;
import com.example.magazzino.model.entity.CustomerEntity;
import com.example.magazzino.model.entity.OrderEntity;
import com.example.magazzino.model.entity.ProductEntity;
import com.example.magazzino.repository.CustomerRepository;
import com.example.magazzino.repository.OrderRepository;
import com.example.magazzino.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(ProductService productService, CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.productService = productService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderEntity create(OrderDto orderDto) {
        OrderEntity order = OrderMapper.mapToOrderEntity(orderDto);

        Optional<OrderEntity> orderEntityOptional = orderRepository
                .searchByOrderName(order.getOrderName());
        if (orderEntityOptional.isPresent()) {
            throw new IllegalStateException("Order already exists");
        }

        // Prendo un cliente
        CustomerEntity customer = customerRepository.findById(orderDto.getCustomer().getIdCustomer()).
                orElseThrow(() -> new IllegalStateException("Customer with ID "
                        + orderDto.getCustomer().getIdCustomer() + " does not exist"));

        Double totalPayment = order.getTotalPayment();
        List<ProductEntity> productEntities = new ArrayList<>();

        // Controllo se il prodotto esiste oppure ce ne sia abbastanza
        for (ProductDto productOrder : orderDto.getProducts()) {
            ProductEntity product = productRepository.findById(productOrder.getIdProduct())
                    .orElseThrow(() -> new IllegalStateException("Product with ID " + productOrder.getIdProduct() + " does not exist"));

            if (product.getStock() < productOrder.getQuantity()) {
                throw new IllegalStateException("Not enough stock for product " + productOrder.getIdProduct());
            }

            // Aggiorna stock e totale pagamento
            product.setStock(product.getStock() - productOrder.getQuantity());
            totalPayment += product.getPrice() * productOrder.getQuantity();

            // Salva il prodotto aggiornato e aggiungilo all'ordine
            productRepository.save(product);
            productEntities.add(product);
        }
        // Crea l'ordine
//        order.setCustomer(customer);
//        order.setProducts(productEntities);
//        order.setTotalPayment(totalPayment);
//        order.setStatus(true); // Imposta lo stato dell'ordine a "confermato"

        // Aggiorna il pagamento del cliente
        order.setTotalPayment(totalPayment);
        customerRepository.save(customer);

        orderRepository.save(order);
        return order;
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

}
