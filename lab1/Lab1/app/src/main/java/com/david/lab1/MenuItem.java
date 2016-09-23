package com.david.lab1;

import java.util.ArrayList;

/**
 * Created by david on 9/23/16.
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

}
