package com.tns.fooddeliverysystem.services;

import java.util.ArrayList;
import java.util.List;
import com.tns.fooddeliverysystem.entities.Customer;

public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Customer getCustomer(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id)
                return c;
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
