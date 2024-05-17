package objects.tests;

import objects.Point;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private Point point6;
    private Point origin;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        point1 = new Point(1, 1);
        point2 = new Point(4, 5);
        point3 = new Point(1, 1);
        point4 = new Point(-1, -1);
        point5 = new Point(1, -1);
        point6 = new Point(1, 1);
        origin = new Point(0, 0);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        point1 = null;
        point2 = null;
        point3 = null;
        point4 = null;
        point5 = null;
        origin = null;
    }

    @org.junit.jupiter.api.Test
    void distance() {
        assertEquals(5.0, point1.distance(point2), "The distance between point1 and point2 should be 5.0");
        assertEquals(0.0, point1.distance(point3), "The distance between point1 and point3 should be 0.0");
        assertEquals(Math.sqrt(2), point1.distance(origin), "The distance between point1 and origin should be sqrt(2)");
        assertEquals(2.0, point1.distance(point5), "The distance between point1 and point5 should be 2.0");
        assertEquals(Math.sqrt(2), point4.distance(origin), "The distance between point4 and origin should be sqrt(2)");
        assertThrows(IllegalArgumentException.class, () -> {
            point1.distance(null);
        }, "Distance to a null point should throw IllegalArgumentException");
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertTrue(point1.equals(point3), "point1 and point3 should be equal");
        assertFalse(point1.equals(point2), "point1 and point2 should not be equal");
        assertFalse(point1.equals(null), "point1 should not be equal to null");
        assertFalse(point1.equals("Some String"), "point1 should not be equal to an object of different type");
        assertTrue(point1.equals(point1), "point1 should be equal to itself");
        assertTrue(point1.equals(point3) && point3.equals(point1), "equals should be symmetric");
        assertTrue(point1.equals(point3) && point3.equals(point6) && point1.equals(point6), "equals should be transitive");
    }

    @org.junit.jupiter.api.Test
    void getX() {
        assertEquals(1, point1.getX(), "X coordinate of point1 should be 1");
        assertEquals(4, point2.getX(), "X coordinate of point2 should be 4");
        assertEquals(-1, point4.getX(), "X coordinate of point4 should be -1");
        assertEquals(0, origin.getX(), "X coordinate of origin should be 0");
    }

    @org.junit.jupiter.api.Test
    void getY() {
        assertEquals(1, point1.getY(), "Y coordinate of point1 should be 1");
        assertEquals(5, point2.getY(), "Y coordinate of point2 should be 5");
        assertEquals(-1, point4.getY(), "Y coordinate of point4 should be -1");
        assertEquals(0, origin.getY(), "Y coordinate of origin should be 0");
    }
}
