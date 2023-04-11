package com.amigoscode.livestockplatform.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart", schema = "public", catalog = "postgres")
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "buyer_id")
    private Integer buyerId;
    @Basic
    @Column(name = "cart_status")
    private String cartStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return id == that.id && orderId == that.orderId && productId == that.productId && Objects.equals(quantity, that.quantity) && Objects.equals(buyerId, that.buyerId) && Objects.equals(cartStatus, that.cartStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, quantity, buyerId, cartStatus);
    }
}
