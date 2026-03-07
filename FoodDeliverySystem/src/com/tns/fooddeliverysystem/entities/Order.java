package com.tns.fooddeliverysystem.entities;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items = new HashMap<>();
    private String status = "Pending";
    private DeliveryPerson deliveryPerson;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addItem(FoodItem item, int qty) {
        items.put(item, qty);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setDeliveryPerson(DeliveryPerson dp) {
        this.deliveryPerson = dp;
    }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId +
                ", customer=" + customer.getUsername() +
                ", items=" + items +
                ", status='" + status + "'" +
                ", deliveryPerson=" + 
                (deliveryPerson == null ? "Not Assigned" : deliveryPerson.getName()) +
                "}";
    }
}

