package objects.tests;

import objects.Threshold;
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
    private Point point15;
    private Point point16;
    private Point point17;

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
        point15 = new Point(Threshold.TOLERANCE, Threshold.TOLERANCE);
        point16 = new Point(1000.0, 1000.0);
        point17 = new Point(92.23372036854776, 92.23372036854776);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        point1 = null;
        point2 = null;
        point3 = null;
        point4 = null;
        point5 = null;
        point6 = null;
        point7 = null;
        point8 = null;
        point9 = null;
        point10 = null;
        point11 = null;
        point12 = null;
        point13 = null;
        point14 = null;
        origin = null;
        point15 = null;
        point16 = null;
        point17 = null;
    }

    @org.junit.jupiter.api.Test
    void distance() {
        assertEquals(5.0, point1.distance(point2), "The distance between point1 and point2 should be 5.0");
        assertEquals(0.0, point1.distance(point3), "The distance between point1 and point3 should be 0.0");
        assertEquals(Math.sqrt(2), point1.distance(origin), Threshold.TOLERANCE, "The distance between point1 and origin should be sqrt(2)");
        assertEquals(2.0, point1.distance(point5), "The distance between point1 and point5 should be 2.0");
        assertEquals(Math.sqrt(2), point4.distance(origin), Threshold.TOLERANCE, "The distance between point4 and origin should be sqrt(2)");
        assertEquals(0.0, origin.distance(origin), "The distance between origin and itself should be 0.0");
        assertEquals(0.0, point11.distance(point12), Threshold.TOLERANCE, "The distance between point11 and point12 should be 0.0");
        assertEquals(0.0, point13.distance(point14), Threshold.TOLERANCE, "The distance between point13 and point14 should be 0.0");
//        assertThrows(IllegalArgumentException.class, () -> {
//            point1.distance(null);
//        }, "Distance to a null point should throw IllegalArgumentException");
        assertEquals(-1, point1.distance(null), "Distance to a null point should return -1");
        assertEquals(0,origin.distance(point15), Threshold.TOLERANCE, "The distance between origin and point15 should be ~0.0");
        assertEquals(0, point15.distance(origin), Threshold.TOLERANCE, "The distance between point15 and origin should be ~0.0");
        assertEquals(1283.7753841197672, point16.distance(point17), Threshold.TOLERANCE, "The distance between point16 and point17 should be ~1283.7753841197672");
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
        assertFalse(point16.equals(point17), "point16 and point17 should not be equal within the threshold");
        assertFalse(point17.equals(point16), "point17 and point16 should not be equal within the threshold");
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
        assertEquals(1.0000001, point9.getX(), "X coordinate of point9 should be 1.0000001");
        assertEquals(1000, point16.getX(), "X coordinate of point16 should be 1000");
        assertEquals(92.23372036854776, point17.getX(), "X coordinate of point17 should be 92.23372036854776");
    }

    @org.junit.jupiter.api.Test
    void getY() {
        assertEquals(1, point1.getY(), "Y coordinate of point1 should be 1");
        assertEquals(5, point2.getY(), "Y coordinate of point2 should be 5");
        assertEquals(-1, point4.getY(), "Y coordinate of point4 should be -1");
        assertEquals(0, origin.getY(), "Y coordinate of origin should be 0");
        assertEquals(2.5, point7.getY(), "Y coordinate of point7 should be 2.5");
        assertEquals(0.1 + 0.7, point8.getY(), "Y coordinate of point8 should be 0.8");
        assertEquals(1.0000001, point9.getX(), "X coordinate of point9 should be 1.0000001");
        assertEquals(99.0000001, point9.getY(), "Y coordinate of point9 should be 99.0000001");
        assertEquals(1000, point16.getY(), "Y coordinate of point16 should be 1000");
        assertEquals(92.23372036854776, point17.getY(), "Y coordinate of point17 should be 92.23372036854776");
    }

    @org.junit.jupiter.api.Test
    void distanceWithNegativeCoordinates() {
        Point pointA = new Point(-3, -4);
        Point pointB = new Point(-1, -1);
        assertEquals(Math.sqrt(13), pointA.distance(pointB), Threshold.TOLERANCE, "The distance between pointA and pointB should be sqrt(13)");
    }

    @org.junit.jupiter.api.Test
    void distanceWithSameCoordinates() {
        Point pointA = new Point(3, 4);
        assertEquals(0.0, pointA.distance(pointA), "The distance between pointA and itself should be 0.0");
        Point pointB = new Point(3, 4);
        assertEquals(0.0, pointB.distance(pointB), "The distance between pointA and itself should be 0.0");

    }

    @org.junit.jupiter.api.Test
    void equalsWithDifferentTypes() {
        Point pointA = new Point(1, 1);
        assertFalse(pointA.equals("Some String"), "pointA should not be equal to an object of different type");
        assertFalse(point11.equals(point14), "point11 and point14 should not be equal");
        assertFalse(point14.equals(point11), "point11 and point14 should not be equal");
    }

    @org.junit.jupiter.api.Test
    void equalsWithSameCoordinates() {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(1, 1);
        assertTrue(pointA.equals(pointB), "pointA and pointB should be equal");
    }

    @org.junit.jupiter.api.Test
    void getXWithNegativeCoordinate() {
        Point pointA = new Point(-3, 4);
        assertEquals(-3, pointA.getX(), "X coordinate of pointA should be -3");
        Point pointB = new Point(-1, 1);
        assertEquals(-1, pointB.getX(), "X coordinate of pointB should be -1");
    }

    @org.junit.jupiter.api.Test
    void getYWithNegativeCoordinate() {
        Point pointA = new Point(-982, -54.917);
        assertEquals(-54.917, pointA.getY(), "Y coordinate of pointA should be -54.917");
        Point pointB = new Point(98.34, -32.9);
        assertEquals(-32.9, pointB.getY(), "Y coordinate of pointB should be -32.9");
    }

    @org.junit.jupiter.api.Test
    void distanceWithNullPoint() {
        assertEquals(-1, point1.distance(null), "Distance to a null point should return -1");
    }

    @org.junit.jupiter.api.Test
    void equalsWithNullPoint() {
        assertFalse(point1.equals(null), "point1 should not be equal to null");
    }

    @org.junit.jupiter.api.Test
    void equalsWithItself() {
        assertTrue(point1.equals(point1), "point1 should be equal to itself");
    }

    @org.junit.jupiter.api.Test
    void equalsSymmetric() {
        assertTrue(point1.equals(point3) && point3.equals(point1), "equals should be symmetric");
    }

    @org.junit.jupiter.api.Test
    void equalsTransitive() {
        assertTrue(point1.equals(point3) && point3.equals(point6) && point1.equals(point6), "equals should be transitive (without threshold)");
    }
}

