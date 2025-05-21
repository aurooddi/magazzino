package com.example.magazzino.model.entity;

import jakarta.persistence.*;

@Entity
public class QuantityEntity {

    @EmbeddedId
    QuantityKey id;

    @ManyToOne
    @MapsId("idCustomer")
    @JoinColumn(name = "id_customer")
    CustomerEntity customerEntity;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
    ProductEntity productEntity;

    Integer quantity;

    public QuantityEntity() {
    }

    public QuantityEntity(QuantityKey id, CustomerEntity customerEntity, ProductEntity productEntity, Integer quantity) {
        this.id = id;
        this.customerEntity = customerEntity;
        this.productEntity = productEntity;
        this.quantity = quantity;
    }

    public QuantityKey getId() {
        return id;
    }

    public void setId(QuantityKey id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
