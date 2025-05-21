package com.example.magazzino.model.entity;

import com.example.magazzino.model.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "order_table")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private CustomerEntity customer;

    @Column(unique = true)
    private String orderName;
    @Column(nullable = false)
    private Double totalPayment;
    private Boolean status;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
            // aggiungere quantità
            // CASCADE
    )
    private List<ProductEntity> products;

    public OrderEntity() {
    }

    public OrderEntity(Long id, CustomerEntity customer, String orderName, Double totalPayment, Boolean status, List<ProductEntity> products) {
        this.id = id;
        this.customer = customer;
        this.orderName = orderName;
        this.totalPayment = totalPayment;
        this.status = status;
        this.products = products;
    }

    public OrderEntity(String orderName, Double totalPayment, Boolean status, List<ProductEntity> products) {
        this.orderName = orderName;
        this.totalPayment = totalPayment;
        this.status = status;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
