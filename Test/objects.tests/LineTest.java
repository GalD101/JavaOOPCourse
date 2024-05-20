package objects.tests;

import objects.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import objects.Point;
import objects.Line;
import objects.Constants.*;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private Line line5;
    private Line line6;
    private Line line7;
    private Line line8;
    private Line line9;
    private Line line10;
    private Line line11;
    private Line line12;
    private Line verticalLine;
    private Line horizontalLine;
    private Line nullLine1;
    private Line nullLine2;

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
        nullLine1 = new Line(null, end1);
        nullLine2 = new Line(start1, null);
        line2 = new Line(start2, end2);
        line3 = new Line(start3, end3);
        line4 = new Line(start4, end4);
        line5 = new Line(1.0, 1.0, 4.0, 5.0);
        line6 = new Line(0.0, 0.0, 0.0, 4.0);
        line7 = new Line(0.0, 4.0, 4.0, 0.0);
        line8 = new Line(1.0, 1.0, 1.0, 5.0);
        line9 = new Line(1.0, 1.0, 5.0, 1.0);
        line10 = new Line(new Point(1.5, 2.5),new Point( 3.5, 4.5));
        line11 = new Line(0.1, 0.2, 0.3, 0.4);
        line12  = new Line (0.93, 557.92, 0.93, 557.92);
        // TODO ADD TESTS
        verticalLine = new Line(start5, end5);
        horizontalLine = new Line(start6, end6);
    }

    @Test
    void length() {
        assertEquals(5.0, line1.length(), Constants.TOLERANCE, "The length of line1 should be 5.0");
        assertEquals(4.0, line3.length(), Constants.TOLERANCE, "The length of line3 should be 4.0");
        assertEquals(5.656854249492381, line4.length(), Constants.TOLERANCE, "The length of line4 should be 5.656854249492381");
        assertEquals(2*Math.sqrt(2), line10.length(), Constants.TOLERANCE, "The length of line5 should be 2*sqrt(2)");
        assertEquals(0.2828427125, line11.length(), Constants.TOLERANCE, "The length of line11 should be 0.28284271247461906");
        assertEquals(0.0, line12.length(), Constants.TOLERANCE, "The length of line12 should be 0.0");
    }



    @Test
    void start() {
        assertEquals(1, line1.start().getX(), "The start X coordinate of line1 should be 1");
        assertEquals(1, line1.start().getY(), "The start Y coordinate of line1 should be 1");
        assertEquals(0, nullLine1.start().getX(), "The start X coordinate of nullLine1 should be 0");
        assertEquals(0, nullLine1.start().getY(), "The start Y coordinate of nullLine1 should be 0");
        assertEquals(4, nullLine1.end().getY(), "The end Y coordinate of nullLine1 should be 0");
        assertEquals(5, nullLine1.end().getY(), "The end Y coordinate of nullLine1 should be 0");
    }

    @Test
    void end() {
        assertEquals(4, line1.end().getX(), "The end X coordinate of line1 should be 4");
        assertEquals(5, line1.end().getY(), "The end Y coordinate of line1 should be 5");
    }
    @Test
    void intersectionWith() {
        Line intersectingLine = new Line(new Point(1, 5), new Point(5, 1));
        Point intersection = line1.intersectionWith(intersectingLine);
        assertNotNull(intersection, "line1 should intersect with intersectingLine");
        assertEquals(19.0/7.0, intersection.getX(), Constants.TOLERANCE, "The X coordinate of the intersection should be 3");
        assertEquals(23.0/7.0, intersection.getY(), Constants.TOLERANCE, "The Y coordinate of the intersection should be 3");
        Line nonIntersectingLine = new Line(new Point(5, 5), new Point(6, 6));
        assertNull(line1.intersectionWith(nonIntersectingLine), "line1 should not intersect with nonIntersectingLine");
    }

    @Test
    void testEquals() {
        assertTrue(line1.equals(line2), "line1 and line2 should be equal");
        assertFalse(line1.equals(line3), "line1 and line3 should not be equal");
    }
}
