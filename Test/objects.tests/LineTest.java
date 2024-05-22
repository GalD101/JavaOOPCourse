package objects.tests;

import objects.Threshold;
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
    private Line line5;
    private Line line6;
    private Line line7;
    private Line line8;
    private Line line9;
    private Line line10;
    private Line line11;
    private Line line12;
    private Line linepoint;
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
        linepoint = new Line(new Point(0, 0), new Point(0, 0));
        verticalLine = new Line(start5, end5);
        horizontalLine = new Line(start6, end6);
    }

    @Test
    void testLength() {
        assertEquals(5.0, line1.length(), Threshold.TOLERANCE, "The length of line1 should be 5.0");
        assertEquals(4.0, line3.length(), Threshold.TOLERANCE, "The length of line3 should be 4.0");
        assertEquals(5.656854249492381, line4.length(), Threshold.TOLERANCE, "The length of line4 should be 5.656854249492381");
        assertEquals(2*Math.sqrt(2), line10.length(), Threshold.TOLERANCE, "The length of line5 should be 2*sqrt(2)");
        assertEquals(0.2828427125, line11.length(), Threshold.TOLERANCE, "The length of line11 should be 0.28284271247461906");
        assertEquals(0.0, line12.length(), Threshold.TOLERANCE, "The length of line12 should be 0.0");
        assertEquals(4.0, verticalLine.length(), Threshold.TOLERANCE, "The length of verticalLine should be 4.0");
        assertEquals(4.0, horizontalLine.length(), Threshold.TOLERANCE, "The length of horizontalLine should be 4.0");
    }

    @Test
    void testMiddle() {
        Point expected;
        Point actual;

        expected = new Point(2.5, 3.0);
        actual = line1.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line1 should be 2.5");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line1 should be 3.0");

        expected = new Point(0.0, 2.0);
        actual = line3.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line3 should be 0.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line3 should be 2.0");

        expected = new Point(2.0, 2.0);
        actual = line4.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line4 should be 2.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line4 should be 2.0");

        expected = new Point(2.5, 3.0);
        actual = line5.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line5 should be 1.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line5 should be 3.0");

        expected = new Point(0.0, 2.0);
        actual = line6.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line6 should be 0.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line6 should be 2.0");

        expected = new Point(2.0, 2.0);
        actual = line7.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line7 should be 2.5");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line7 should be 1.0");

        expected = new Point(1.0, 3.0);
        actual = line8.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line8 should be 1.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line8 should be 3.0");

        expected = new Point(3.0, 1.0);
        actual = line9.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line9 should be 3.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line9 should be 1.0");

        expected = new Point(2.5, 3.5);
        actual = line10.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line10 should be 2.5");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line10 should be 3.5");

        expected = new Point(0.2, 0.3);
        actual = line11.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line11 should be 0.2");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line11 should be 0.3");

        expected = new Point(0.93, 557.92);
        actual = line12.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of line12 should be 0.93");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of line12 should be 557.92");

        expected = new Point(1.0, 3.0);
        actual = verticalLine.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of verticalLine should be 1.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of verticalLine should be 3.0");

        expected = new Point(3.0, 1.0);
        actual = horizontalLine.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of horizontalLine should be 3.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of horizontalLine should be 1.0");

        expected = new Point(0.0, 0.0);
        actual = linepoint.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of linepoint should be 0.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of linepoint should be 0.0");
    }

    @Test
    void testStart() {
        assertEquals(1, line1.start().getX(), "The start X coordinate of line1 should be 1");
        assertEquals(1, line1.start().getY(), "The start Y coordinate of line1 should be 1");
        assertEquals(0, nullLine1.start().getX(), "The start X coordinate of nullLine1 should be 0");
        assertEquals(0, nullLine1.start().getY(), "The start Y coordinate of nullLine1 should be 0");
        assertEquals(4, nullLine1.end().getX(), "The end X coordinate of nullLine1 should be 4");
        assertEquals(5, nullLine1.end().getY(), "The end Y coordinate of nullLine1 should be 5");
    }

    @Test
    void testEnd() {
        assertEquals(4, line1.end().getX(), "The end X coordinate of line1 end should be 4");
        assertEquals(5, line1.end().getY(), "The end Y coordinate of line1 end should be 5");
        assertEquals(0, nullLine2.end().getX(), "The end X coordinate of nullLine2 end should be 0");
        assertEquals(0, nullLine2.end().getY(), "The end Y coordinate of nullLine2 end should be 0");
        assertEquals(1, nullLine2.start().getX(), "The start X coordinate of nullLine2 start should be 1");
        assertEquals(1, nullLine2.start().getY(), "The start Y coordinate of nullLine2 start should be 1");
        assertEquals(0, linepoint.end().getX(), "The end X coordinate of linepoint end should be 0");
        assertEquals(0, linepoint.end().getY(), "The end Y coordinate of linepoint end should be 0");
        assertEquals(1, verticalLine.end().getX(), "The end X coordinate of verticalLine end should be 1");
        assertEquals(5, verticalLine.end().getY(), "The end Y coordinate of verticalLine end should be 5");
        assertEquals(5, horizontalLine.end().getX(), "The end X coordinate of horizontalLine end should be 5");
        assertEquals(1, horizontalLine.end().getY(), "The end Y coordinate of horizontalLine end should be 1");
        assertEquals(0, linepoint.end().getX(), "The end X coordinate of linePoint end should be 0");
    }

    @Test
    void testIsIntersecting() {
        assertTrue(line1.isIntersecting(line1), "line1 should intersect with itself");
        assertTrue(line1.isIntersecting(line2), "line1 should intersect with line2");
        assertTrue(line2.isIntersecting(line1), "line2 should intersect with line1");
        assertTrue(line1.isIntersecting(verticalLine), "line1 should intersect with verticalLine");
        assertTrue(line1.isIntersecting(horizontalLine), "line1 should intersect with horizontalLine");
        assertFalse(line1.isIntersecting(linepoint), "line1 should not intersect with linepoint");
        assertFalse(line1.isIntersecting(line3), "line1 should not intersect with line3");

        assertFalse(linepoint.isIntersecting(line1, line2), "linepoint should not intersect with line1 and line2");
        assertFalse(linepoint.isIntersecting(line1, line3), "linepoint should not intersect with line1 and line3");
        assertFalse(linepoint.isIntersecting(line1, line4), "linepoint should not intersect with line1 and line4");
        assertFalse(linepoint.isIntersecting(line1, line5), "linepoint should not intersect with line1 and line5");
        assertFalse(line2.isIntersecting(line1, line3), "line2 should not intersect with line1 and line3");
        assertTrue(line2.isIntersecting(line1, line5), "line2 should intersect with line1 and line5");
        assertFalse(line2.isIntersecting(line1, line6), "line2 should not intersect with line1 and line6");

        assertTrue(line1.isIntersecting(line2), "line1 should intersect with line2");
        assertTrue(line1.isIntersecting(verticalLine), "line1 should intersect with verticalLine");
        assertTrue(line1.isIntersecting(horizontalLine), "line1 should intersect with horizontalLine");
        assertFalse(line1.isIntersecting(linepoint), "line1 should not intersect with linepoint");
        assertFalse(line1.isIntersecting(line3), "line1 should not intersect with line3");

        // Special cases
        Line verticalLine2 = new Line(new Point(2, 0), new Point(2, 4));
        Line horizontalLine2 = new Line(new Point(0, 2), new Point(4, 2));
        assertTrue(verticalLine2.isIntersecting(horizontalLine2), "verticalLine2 should intersect with horizontalLine2");

        Line overlappingLine = new Line(new Point(1, 1), new Point(4, 5));
        assertTrue(line1.isIntersecting(overlappingLine), "line1 should intersect with overlappingLine");

        Line intersectAtStart = new Line(new Point(1, 1), new Point(2, 2));
        assertTrue(line1.isIntersecting(intersectAtStart), "line1 should intersect with intersectAtStart");

        Line barelyIntersectingLine = new Line(new Point(8, -6), new Point(1+ Threshold.TOLERANCE, 1));
        assertTrue(line1.isIntersecting(barelyIntersectingLine), "line1 should intersect with barelyIntersectingLine");
    }

    @Test
    void testIntersectionWith() {
        Line intersectingLine = new Line(new Point(1, 5), new Point(5, 1));
        Point intersection = line1.intersectionWith(intersectingLine);
        assertNotNull(intersection, "line1 should intersect with intersectingLine");
        assertEquals(19.0/7.0, intersection.getX(), Threshold.TOLERANCE, "The X coordinate of the intersection should be 3");
        assertEquals(23.0/7.0, intersection.getY(), Threshold.TOLERANCE, "The Y coordinate of the intersection should be 3");
        Line nonIntersectingLine = new Line(new Point(5, 5), new Point(6, 6));
        assertNull(line1.intersectionWith(nonIntersectingLine), "line1 should not intersect with nonIntersectingLine");
        assertNull(line1.intersectionWith(null), "line1 should not intersect with null");
        assertNull(nullLine1.intersectionWith(null), "nullLine1 should not intersect with null");

    }

    @Test
    void testEquals() {
        assertTrue(line1.equals(line2), "line1 and line2 should be equal");
        assertTrue(line1.equals(line5), "line1 and line5 should be equal");
        assertTrue(line1.equals(line1), "line1 should be equal to itself");
        assertFalse(line1.equals(null), "line1 should not be equal to null");
        assertFalse(line1.equals(line3), "line1 and line3 should not be equal");
        assertFalse(line1.equals(line4), "line1 and line4 should not be equal");
        assertFalse(line1.equals(line6), "line1 and line6 should not be equal");
        assertFalse(line1.equals(line7), "line1 and line7 should not be equal");
        assertFalse(line1.equals(line8), "line1 and line8 should not be equal");
    }
}
