package com.java.zero;

import com.java.zero.cook.impl.ACook;
import com.java.zero.cook.impl.BCook;
import com.java.zero.cook.impl.CCook;
import com.java.zero.cook.impl.DCook;
import com.java.zero.cuisine.impl.ACuisine;
import com.java.zero.cuisine.impl.BCuisine;
import com.java.zero.cuisine.impl.CCuisine;
import com.java.zero.cuisine.impl.DCuisine;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void test() {

        ACook aCook = new ACook();
        BCook bCook = new BCook();
        CCook cCook = new CCook();
        DCook dCook = new DCook();

        ACuisine aCuisine = new ACuisine(aCook);
        BCuisine bCuisine = new BCuisine(bCook);
        CCuisine cCuisine = new CCuisine(cCook);
        DCuisine dCuisine = new DCuisine(dCook);

        Waiter waiter = new Waiter();
        waiter.order(aCuisine);
        waiter.order(bCuisine);
        waiter.order(cCuisine);
        waiter.order(dCuisine);

        waiter.display();
    }
}
