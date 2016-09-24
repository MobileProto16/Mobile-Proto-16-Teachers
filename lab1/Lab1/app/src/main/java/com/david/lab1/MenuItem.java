package com.david.lab1;

import java.util.ArrayList;

/**
 * A MenuItem has a name and ingredients.
 */

public class MenuItem {

    private ArrayList<String> ingredients;
    private String name;

    public MenuItem(String n) {
        this.name = n;
    }

    public void addIngredient(String i) {
        ingredients.add(i);
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
