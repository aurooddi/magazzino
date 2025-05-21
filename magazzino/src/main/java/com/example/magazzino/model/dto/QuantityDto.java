package com.example.magazzino.model.dto;

public class QuantityDto {

  private Integer quantity;
  private Long idCustomer;
  private Long idProduct;

  public QuantityDto() {
  }

    public QuantityDto(Integer quantity, Long idCustomer, Long idProduct) {
        this.quantity = quantity;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
