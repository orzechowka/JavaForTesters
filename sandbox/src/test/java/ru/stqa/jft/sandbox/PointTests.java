package ru.stqa.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Anna on 2016-12-08.
 */
public class PointTests {

    @Test
    public void testPoint() {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(5, 9);
        Assert.assertEquals(p1.distance(p2), 3.0);
    }

    @Test
    public void testPoint1() {
        Point p1 = new Point(9, 5);
        Point p2 = new Point(4, 5);
        Assert.assertEquals(p1.distance(p2), 3.0);
    }

    @Test
    public void testPoint2() {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(1, 5);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }

    @Test
    public void testPoint3() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(25, 5);
        Assert.assertEquals(p1.distance(p2), 20.0);
    }
}
