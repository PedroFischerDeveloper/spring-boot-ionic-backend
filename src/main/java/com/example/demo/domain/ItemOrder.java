package com.example.demo.domain;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemOrderPk id = new ItemOrderPk();

    private Double discount;
    private Integer quantity;
    private Double price;

    public ItemOrder() {};

    public ItemOrder(Order order, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    };

    public Product getProduct() {
        return id.getProduct();
    };



    public ItemOrderPk getId() {
        return id;
    }

    public void setId(ItemOrderPk id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrder)) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return getId().equals(itemOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
