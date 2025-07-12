package com.aurionpro.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.order.Order;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Order> orders;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrderHistory() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', totalOrders=" + orders.size() + '}';
    }
}