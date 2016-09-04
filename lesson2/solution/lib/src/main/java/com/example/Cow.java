package com.example;

import java.util.Random;

/**
 * Created by abrahams on 6/22/16.
 */
public class Cow extends Animal {

    public Cow(String name, String color) {
        super(4, (new Random()).nextInt(26), name, color, "Cow");
    }

    public void grow() {
        this.setWeight((int)(this.getWeight() * 5));
    }

}
