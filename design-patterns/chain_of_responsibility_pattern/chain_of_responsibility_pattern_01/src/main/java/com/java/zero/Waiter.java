package com.java.zero;

import com.java.zero.cuisine.Cuisine;

import java.util.ArrayList;
import java.util.List;

public class Waiter {

    public static final List<Cuisine> list = new ArrayList<Cuisine>(4);

    public void order(Cuisine cuisine) {
        list.add(cuisine);
    }

    public void display() {
        for (Cuisine cuisine : list) {
            cuisine.display();
        }
    }
}
