package objects.tests;

import objects.Constants;
import objects.Point;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private Point point6;
    private Point point7;
    private Point point8;
    private Point point9;
    private Point point10;
    private Point point11;
    private Point point12;
    private Point point13;
    private Point point14;
    private Point origin;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        point1 = new Point(1, 1);
        point2 = new Point(4, 5);
        point3 = new Point(1, 1);
        point4 = new Point(-1, -1);
        point5 = new Point(1, -1);
        point6 = new Point(1, 1);
        point7 = new Point(1.5, 2.5);
        point8 = new Point(2.99 * 10, 0.1 + 0.7);
        point9 = new Point(1.0000001, 99.0000001);
        point10 = new Point(1.0000002, 99.0000002);
        point11 = new Point(4.9999999, 2);
        point12 = new Point(5, 2);
        point13 = new Point(-23, -4.9999999);
        point14 = new Point(-23, -5);
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
        assertEquals(Math.sqrt(2), point1.distance(origin), Constants.TOLERANCE, "The distance between point1 and origin should be sqrt(2)");
        assertEquals(2.0, point1.distance(point5), "The distance between point1 and point5 should be 2.0");
        assertEquals(Math.sqrt(2), point4.distance(origin), Constants.TOLERANCE, "The distance between point4 and origin should be sqrt(2)");
        assertEquals(0.0, origin.distance(origin), "The distance between origin and itself should be 0.0");
        assertEquals(0.0, point11.distance(point12), Constants.TOLERANCE, "The distance between point11 and point12 should be 0.0");
        assertEquals(0.0, point13.distance(point14), Constants.TOLERANCE, "The distance between point13 and point14 should be 0.0");
//        assertThrows(IllegalArgumentException.class, () -> {
//            point1.distance(null);
//        }, "Distance to a null point should throw IllegalArgumentException");
        assertEquals(-1, point1.distance(null), "Distance to a null point should return -1");
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
        assertTrue(point9.equals(point10), "point9 and point10 should be equal within the threshold");
        assertTrue(point11.equals(point12), "point11 and point12 should be equal within the threshold");
        assertTrue(point13.equals(point14), "point13 and point14 should be equal within the threshold");

    }

    @org.junit.jupiter.api.Test
    void getX() {
        assertEquals(1, point1.getX(), "X coordinate of point1 should be 1");
        assertEquals(4, point2.getX(), "X coordinate of point2 should be 4");
        assertEquals(-1, point4.getX(), "X coordinate of point4 should be -1");
        assertEquals(0, origin.getX(), "X coordinate of origin should be 0");
        assertEquals(1.5, point7.getX(), "X coordinate of point7 should be 1.5");
        assertEquals(2.99 * 10, point8.getX(), "X coordinate of point8 should be 29.9");
        assertEquals(1.0000001, point9.getX(), "X coordinate of point9 should be 1.0000001");
    }

    @org.junit.jupiter.api.Test
    void getY() {
        assertEquals(1, point1.getY(), "Y coordinate of point1 should be 1");
        assertEquals(5, point2.getY(), "Y coordinate of point2 should be 5");
        assertEquals(-1, point4.getY(), "Y coordinate of point4 should be -1");
        assertEquals(0, origin.getY(), "Y coordinate of origin should be 0");
        assertEquals(2.5, point7.getY(), "Y coordinate of point7 should be 2.5");
        assertEquals(0.1 + 0.7, point8.getY(), "Y coordinate of point8 should be 0.8");
    }
}
