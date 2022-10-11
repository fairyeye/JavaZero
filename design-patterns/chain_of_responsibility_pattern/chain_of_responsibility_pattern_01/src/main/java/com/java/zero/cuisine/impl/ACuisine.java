package com.java.zero.cuisine.impl;

import com.java.zero.cook.Cook;
import com.java.zero.cook.impl.ACook;
import com.java.zero.cuisine.Cuisine;

public class ACuisine extends Cuisine {


    protected Cook cook;

    public ACuisine(Cook cook) {
        this.cook = cook;
    }

    public void display() {
        cook.doCook();
    }
}
