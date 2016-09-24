package com.david.lab1;

import android.view.Menu;

import java.util.ArrayList;

/**
 * An order has an ArrayList of MenuItems and a Customer.
 */

public class Order {

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
}
