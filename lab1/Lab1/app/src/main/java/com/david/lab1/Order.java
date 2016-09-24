package com.david.lab1;

import android.view.Menu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An order has an ArrayList of MenuItems and a Customer.
 */

public class Order implements Serializable {

    private Customer customer;
    private ArrayList<MenuItem> items;

    public Order(Customer customer, ArrayList<MenuItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }
    public String toString() {
        return "Order from " + customer.getName();
    }
}
