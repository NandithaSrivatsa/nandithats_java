package com.tns.fooddeliverysystem.services;

import java.util.ArrayList;
import java.util.List;
import com.tns.fooddeliverysystem.entities.*;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private List<DeliveryPerson> deliveryPersons = new ArrayList<>();

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addDeliveryPerson(DeliveryPerson dp) {
        deliveryPersons.add(dp);
    }

    public DeliveryPerson getDeliveryPerson(int id) {
        for (DeliveryPerson dp : deliveryPersons) {
            if (dp.getDeliveryPersonId() == id)
                return dp;
        }
        return null;
    }

    public void assignDeliveryPerson(int orderId, int dpId) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                o.setDeliveryPerson(getDeliveryPerson(dpId));
            }
        }
    }
}