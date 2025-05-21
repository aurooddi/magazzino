package com.example.magazzino.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuantityKey implements Serializable {

    @Column(name = "id_customer")
    Long idCustomer;

    @Column(name = "id_product")
    Long idProduct;

    public QuantityKey(Long idCustomer, Long idProduct) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QuantityKey that = (QuantityKey) o;
        return Objects.equals(idCustomer, that.idCustomer) && Objects.equals(idProduct, that.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, idProduct);
    }
}
