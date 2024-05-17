package objects.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import objects.Point;
import objects.Line;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
//    private Line verticalLine;
//    private Line horizontalLine;

    @BeforeEach
    void setUp() {//TODO ADD TESTS TO MAKE SURE THAT CHANGING POINT AFTER USING IT TO CREATE A LINE WON'T CHANGE THE LINE (ENCAPSULATION)
        Point start1 = new Point(1, 1);
        Point end1 = new Point(4, 5);
        Point start2 = new Point(1, 1);
        Point end2 = new Point(4, 5);
        Point start3 = new Point(0, 0);
        Point end3 = new Point(0, 4);
        Point start4 = new Point(0, 4);
        Point end4 = new Point(4, 0);
        Point start5 = new Point(1, 1);
        Point end5 = new Point(1, 5);
        Point start6 = new Point(1, 1);
        Point end6 = new Point(5, 1);

        line1 = new Line(start1, end1);
        line2 = new Line(start2, end2);
        line3 = new Line(start3, end3);
        line4 = new Line(start4, end4);
//        verticalLine = new Line(start5, end5);
//        horizontalLine = new Line(start6, end6);
    }

    @Test
    void length() {
        assertEquals(5.0, line1.length(), 0.0001, "The length of line1 should be 5.0");
        assertEquals(4.0, line3.length(), 0.0001, "The length of verticalLine should be 4.0");
    }

    @Test
    void start() {
        assertEquals(1, line1.start().getX(), "The start X coordinate of line1 should be 1");
        assertEquals(1, line1.start().getY(), "The start Y coordinate of line1 should be 1");
    }

    @Test
    void end() {
        assertEquals(4, line1.end().getX(), "The end X coordinate of line1 should be 4");
        assertEquals(5, line1.end().getY(), "The end Y coordinate of line1 should be 5");
    }

    @Test
    void calculateSlope() {
//        assertEquals(4.0/3.0, line1.calculateSlope(), 0.0001, "The slope of line1 should be 4/3");
//        assertThrows(ArithmeticException.class, () -> verticalLine.calculateSlope(), "The slope of a vertical line should throw an exception");
    }

    @Test
    void calculateIntercept() {
//        assertEquals(-1.0/3.0, line1.calculateIntercept(), 0.0001, "The intercept of line1 should be -1/3");
//        assertThrows(ArithmeticException.class, () -> verticalLine.calculateIntercept(), "The intercept of a vertical line should throw an exception");
    }

    @Test
    void intersectionWith() {
        Line intersectingLine = new Line(new Point(1, 5), new Point(5, 1));
        Point intersection = line1.intersectionWith(intersectingLine);
        assertNotNull(intersection, "line1 should intersect with intersectingLine");
        assertEquals(3, intersection.getX(), 0.0001, "The X coordinate of the intersection should be 3");
        assertEquals(3, intersection.getY(), 0.0001, "The Y coordinate of the intersection should be 3");

        Line nonIntersectingLine = new Line(new Point(5, 5), new Point(6, 6));
        assertNull(line1.intersectionWith(nonIntersectingLine), "line1 should not intersect with nonIntersectingLine");
    }

    @Test
    void testEquals() {
        assertTrue(line1.equals(line2), "line1 and line2 should be equal");
        assertFalse(line1.equals(line3), "line1 and line3 should not be equal");
    }
}
