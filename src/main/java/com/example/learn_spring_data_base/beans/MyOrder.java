package com.example.learn_spring_data_base.beans;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MyOrder")
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "T_Product_Order_Associations", joinColumns =
    @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name =
            "product_id"))
    private List<Product> products =  new ArrayList<Product>();

    public MyOrder() {
// TODO Auto-generated constructor stub
    }

    public MyOrder(Customer customer) {
        super();
        this.customer = customer;
        this.products = new ArrayList<Product>();
    }

    public MyOrder(Customer customer, List<Product> products) {
        super();
        this.customer = customer;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}