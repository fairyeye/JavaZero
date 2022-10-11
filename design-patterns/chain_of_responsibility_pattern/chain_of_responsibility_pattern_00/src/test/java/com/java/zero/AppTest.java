package com.java.zero;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void test() {
        App app = new App();
        app.order("A");
        app.order("B");
        app.order("C");
        app.order("D");

        app.display();
    }
}
