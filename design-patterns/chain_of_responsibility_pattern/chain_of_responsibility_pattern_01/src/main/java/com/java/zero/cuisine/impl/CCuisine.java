package com.java.zero.cuisine.impl;

import com.java.zero.cook.Cook;
import com.java.zero.cuisine.Cuisine;

public class CCuisine extends Cuisine {

    protected Cook cook;

    public CCuisine(Cook cook) {
        this.cook = cook;
    }

    public void display() {
        cook.doCook();
    }
}
