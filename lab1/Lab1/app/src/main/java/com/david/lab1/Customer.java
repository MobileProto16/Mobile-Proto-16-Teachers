package com.david.lab1;

import java.io.Serializable;

/**
 * Created by david on 9/23/16.
 */

public class Customer implements Serializable {

    private String name;

    public Customer(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
