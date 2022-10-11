package com.java.zero.cuisine.impl;

import com.java.zero.cook.Cook;
import com.java.zero.cuisine.Cuisine;

public class DCuisine extends Cuisine {

    protected Cook cook;

    public DCuisine(Cook cook) {
        this.cook = cook;
    }

    public void display() {
        cook.doCook();
    }
}
