package com.vn.assignmentjava5.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Orderdetails", uniqueConstraints = {@UniqueConstraint(columnNames = {"Orderid","Productid"})})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Integer quantity;

    @ManyToOne @JoinColumn(name = "Productid")
    Product product;
    @ManyToOne@JoinColumn(name = "Orderid")
    Orders orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
