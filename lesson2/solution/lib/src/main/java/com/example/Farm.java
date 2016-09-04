package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by abrahams on 6/22/16.
 */
public class Farm {

    private ArrayList<Animal> animals;

    public Farm(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public ArrayList<Animal> getHeaviestAnimals() {
        ArrayList<Animal> sorted = new ArrayList<>(animals);
        Collections.sort(sorted, new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2)
            {
                return a1.getWeight() - a2.getWeight();
            }
        });
        return sorted;
    }

    public static void main(String[] args) {
    }
}


