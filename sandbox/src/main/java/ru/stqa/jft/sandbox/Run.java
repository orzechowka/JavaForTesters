package ru.stqa.jft.sandbox;

/**
 * Created by Anna on 2016-12-03.
 */
public class Run {

    public static void main(String[] args) {

        /*
        Point p1 = new Point();
        p1.x = 2;
        p1.y = 2;
        Point p2 = new Point();
        p2.x = 1;
        p2.y = 1;

        System.out.println("The distance between points: " + distance(p1, p2));
        */


        Point p1 = new Point(2, 5);
        Point p2 = new Point(5, 9);
        double distance = p1.distance(p2);
        System.out.println("The distance between points p1(" + p1.x +"," + p1.y + ") and p2(" + p2.x + "," + p2.y + "): " + distance);

    }

    /*
    public static double distance(Point p1, Point p2) {
        double pow1 = (p2.x - p1.x) * (p2.x - p1.x);
        double pow2 = (p2.y - p1.y) * (p2.y - p1.y);
        return Math.sqrt(pow1 + pow2);
    }
    */

}
