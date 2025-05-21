package com.example.magazzino.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer stock;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String description, String category, Double price, Integer stock) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public ProductEntity(String description, String category, Double price, Integer stock) {
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "idProduct=" + id +
                ", description='" + description + '\'' +
                ", category='" + category +
                ", price=" + price +
                ", stock='" + stock + '\'' +
                '}';
    }
}
