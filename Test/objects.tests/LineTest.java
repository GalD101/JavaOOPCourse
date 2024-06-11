package objects.tests;

import objects.Rectangle;
import org.junit.jupiter.api.Disabled;
import utils.Threshold;
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
    private Line longestLine;

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
//        nullLine1 = new Line(null, end1);
//        nullLine2 = new Line(start1, null);
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
        longestLine = new Line(new Point(Double.MAX_VALUE, Double.MAX_VALUE), new Point(-Double.MAX_VALUE, -Double.MAX_VALUE));
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

        expected = new Point(0.0, 0.0);
        actual = longestLine.middle();
        assertEquals(expected.getX(), actual.getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of longestLine should be 0.0");
        assertEquals(expected.getY(), actual.getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of longestLine should be 0.0");
    }

    @Test
    void testStart() {
        assertEquals(1, line1.start().getX(), "The start X coordinate of line1 should be 1");
        assertEquals(1, line1.start().getY(), "The start Y coordinate of line1 should be 1");
//        assertEquals(0, nullLine1.start().getX(), "The start X coordinate of nullLine1 should be 0");
//        assertEquals(0, nullLine1.start().getY(), "The start Y coordinate of nullLine1 should be 0");
//        assertEquals(0, nullLine1.end().getX(), "The end X coordinate of nullLine1 should be 4");
//        assertEquals(0, nullLine1.end().getY(), "The end Y coordinate of nullLine1 should be 5");
        assertEquals(Double.MAX_VALUE, longestLine.start().getX(), "The start X coordinate of longestLine should be Double.MAX_VALUE");
        assertEquals(Double.MAX_VALUE, longestLine.start().getY(), "The start Y coordinate of longestLine should be Double.MAX_VALUE");
    }

    @Test
    void testEnd() {
        assertEquals(4, line1.end().getX(), "The end X coordinate of line1 end should be 4");
        assertEquals(5, line1.end().getY(), "The end Y coordinate of line1 end should be 5");
//        assertEquals(0, nullLine2.end().getX(), "The end X coordinate of nullLine2 end should be 0");
//        assertEquals(0, nullLine2.end().getY(), "The end Y coordinate of nullLine2 end should be 0");
//        assertEquals(0, nullLine2.start().getX(), "The start X coordinate of nullLine2 start should be 1");
//        assertEquals(0, nullLine2.start().getY(), "The start Y coordinate of nullLine2 start should be 1");
        assertEquals(0, linepoint.end().getX(), "The end X coordinate of linepoint end should be 0");
        assertEquals(0, linepoint.end().getY(), "The end Y coordinate of linepoint end should be 0");
        assertEquals(1, verticalLine.end().getX(), "The end X coordinate of verticalLine end should be 1");
        assertEquals(5, verticalLine.end().getY(), "The end Y coordinate of verticalLine end should be 5");
        assertEquals(5, horizontalLine.end().getX(), "The end X coordinate of horizontalLine end should be 5");
        assertEquals(1, horizontalLine.end().getY(), "The end Y coordinate of horizontalLine end should be 1");
        assertEquals(0, linepoint.end().getX(), "The end X coordinate of linePoint end should be 0");
        assertEquals(-Double.MAX_VALUE, longestLine.end().getX(), "The end X coordinate of longestLine end should be Double.MAX_VALUE");
        assertEquals(-Double.MAX_VALUE, longestLine.end().getY(), "The end Y coordinate of longestLine end should be -Double.MAX_VALUE");
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
//        assertNull(nullLine1.intersectionWith(null), "nullLine1 should not intersect with null");
    }

    @Test
    void testEquals() {
        assertTrue(line1.equals(line2), "line1 and line2 should be equal");
        assertTrue(line1.equals(line5), "line1 and line5 should be equal");
        assertTrue(line1.equals(line1), "line1 should be equal to itself");
        assertTrue(longestLine.equals(longestLine), "longestLine should be equal to itself");
        assertFalse(line1.equals(null), "line1 should not be equal to null");
        assertFalse(line1.equals(line3), "line1 and line3 should not be equal");
        assertFalse(line1.equals(line4), "line1 and line4 should not be equal");
        assertFalse(line1.equals(line6), "line1 and line6 should not be equal");
        assertFalse(line1.equals(line7), "line1 and line7 should not be equal");
        assertFalse(line1.equals(line8), "line1 and line8 should not be equal");
        assertFalse(longestLine.equals(line1), "longestLine and line1 should not be equal");
        assertFalse(line1.equals(longestLine), "longestLine and line1 should not be equal");
    }

    @Test
    @Disabled("Line will not deal with very big numbers.")
    void testEqualsBigNumbers() {
        assertEquals(Double.MAX_VALUE, longestLine.length(), Threshold.TOLERANCE, "The length of longestLine should be Double.MAX_VALUE");
        assertEquals(0, longestLine.middle().getX(), Threshold.TOLERANCE, "The X coordinate of the middle point of longestLine should be 0");
        assertEquals(0, longestLine.middle().getY(), Threshold.TOLERANCE, "The Y coordinate of the middle point of longestLine should be 0");
    }

    @Test
    public void closestIntersectionToStartOfLineShouldReturnNullWhenNoIntersections() {
        Line line = new Line(new Point(0, 0), new Point(5, 5));
        Rectangle rectangle = new Rectangle(new Point(6, 6), 2, 2);
        assertNull(line.closestIntersectionToStartOfLine(rectangle));
    }

    @Test
    public void closestIntersectionToStartOfLineShouldReturnClosestPointWhenIntersections() {
        Line line = new Line(new Point(0, 0), new Point(5, 5));
        Rectangle rectangle = new Rectangle(new Point(1, 1), 4, 4);
        Point expectedIntersection = new Point(1, 1);
        assertTrue(expectedIntersection.equals(line.closestIntersectionToStartOfLine(rectangle)));
    }

    @Test
    public void closestIntersectionToStartOfLineShouldReturnClosestPointWhenMultipleIntersections() {
        Line line = new Line(new Point(0, 0), new Point(5, 5));
        Rectangle rectangle = new Rectangle(new Point(1, 1), 6, 6);
        Point expectedIntersection = new Point(1, 1);
        assertTrue(expectedIntersection.equals(line.closestIntersectionToStartOfLine(rectangle)));
    }

    @Test
    void testClosestIntersectionEdges() {
        Rectangle rect = new Rectangle(new Point (0, 0), 25, 25);
        Line line = new Line(new Point(15, 35), new Point(35, 15));
        Point intersectionPoint = line.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(25, 25).equals(intersectionPoint));
        Line line2 = new Line(new Point(25, 25), new Point(0, 0));
        Point intersectionPoint2 = line2.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(25, 25).equals(intersectionPoint2));
        Line line3 = new Line(new Point(0, 0), new Point(25, 25));
        Point intersectionPoint3 = line3.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(0, 0).equals(intersectionPoint3));
    }

    @Test
    void testClosestIntersectionHorizontal() {
        Rectangle rect = new Rectangle(new Point (25, 25), new Point(50, 50));
        Line horizontalLine = new Line(new Point (10, 33), new Point(39, 33));
        Point intersectionPoint = horizontalLine.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(25, 33).equals(intersectionPoint));

        Line barelyTouchingLeftOut = new Line(new Point(10, 42), new Point(25, 42));
        Line barelyTouching2LeftOut = new Line(new Point(25, 42), new Point(10, 42));
        Point intersectionPointBarelyTouchingLeftOut = barelyTouchingLeftOut.closestIntersectionToStartOfLine(rect);
        Point intersectionPointBarelyTouching2LeftOut = barelyTouching2LeftOut.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(25, 42).equals(intersectionPointBarelyTouchingLeftOut));
        assertTrue(new Point(25, 42).equals(intersectionPointBarelyTouching2LeftOut));

        Line barelyTouchingLeftIn = new Line(new Point(30, 30), new Point(25, 30));
        Line barelyTouching2LeftIn = new Line(new Point(25, 30), new Point(30, 30));
        Point intersectionPointBarelyTouchingLeftIn = barelyTouchingLeftIn.closestIntersectionToStartOfLine(rect);
        Point intersectionPointBarelyTouching2LeftIn = barelyTouching2LeftIn.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(25, 30).equals(intersectionPointBarelyTouchingLeftIn));
        assertTrue(new Point(25, 30).equals(intersectionPointBarelyTouching2LeftIn));

        Line barelyTouchingRightOut = new Line(new Point(88, 29), new Point(50, 29));
        Line barelyTouching2RightOut = new Line(new Point(50, 29), new Point(88, 29));
        Point intersectionPointBarelyTouchingRightOut = barelyTouchingRightOut.closestIntersectionToStartOfLine(rect);
        Point intersectionPointBarelyTouching2RightOut = barelyTouching2RightOut.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(50, 29).equals(intersectionPointBarelyTouchingRightOut));
        assertTrue(new Point(50, 29).equals(intersectionPointBarelyTouching2RightOut));

        Line barelyTouchingRightIn = new Line(new Point(43, 43), new Point(50, 43));
        Line barelyTouching2RightIn = new Line(new Point(50, 43), new Point(43, 43));
        Point intersectionPointBarelyTouchingRightIn = barelyTouchingRightIn.closestIntersectionToStartOfLine(rect);
        Point intersectionPointBarelyTouching2RightIn = barelyTouching2RightIn.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(50, 43).equals(intersectionPointBarelyTouchingRightIn));
        assertTrue(new Point(50, 43).equals(intersectionPointBarelyTouching2RightIn));

        // Same line
        // this is problematic
        Line sameLinetop = new Line(new Point(30, 25), new Point(57, 25));
//        sameLinetop.closestIntersectionToStartOfLine(rect);
//        assertNull(sameLinetop.closestIntersectionToStartOfLine(rect));
    }

    @Test
    void testClosestIntersectionVertical() {
        Rectangle rect = new Rectangle(new Point (25, 25), new Point(50, 50));
        Line verticalLine = new Line(new Point (30, 46), new Point(30, 10));
        Line verticalLine2 = new Line(new Point (30, 10), new Point(30, 46));
        Point intersectionPoint = verticalLine.closestIntersectionToStartOfLine(rect);
        Point intersectionPoint2 = verticalLine2.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(30, 25).equals(intersectionPoint));
        assertTrue(new Point(30, 25).equals(intersectionPoint2));

        Line verticalLine3 = new Line(new Point (30, 32), new Point(30, 67));
        Line verticalLine4 = new Line(new Point (30, 67), new Point(30, 32));
        Point intersectionPoint3 = verticalLine3.closestIntersectionToStartOfLine(rect);
        Point intersectionPoint4 = verticalLine4.closestIntersectionToStartOfLine(rect);
        assertTrue(new Point(30, 50).equals(intersectionPoint3));
        assertTrue(new Point(30, 50).equals(intersectionPoint4));
        // TODO add more
    }

    @Test
    void testClosestIntersectionIncidental() {
        Rectangle rect = new Rectangle(new Point(25, 25), new Point(50, 50));

        Line incidentalLine = new Line(new Point(42, 62), new Point(52, 29));
        Line incidentalLine2 = new Line(new Point(52, 29), new Point(42, 62));
        assertNotNull(incidentalLine.closestIntersectionToStartOfLine(rect));
        assertNotNull(incidentalLine2.closestIntersectionToStartOfLine(rect));
        assertEquals(50.0, incidentalLine.closestIntersectionToStartOfLine(rect).getY()); // intersection with the bottom line
        assertEquals(50.0, incidentalLine2.closestIntersectionToStartOfLine(rect).getX()); // intersection with the right Line

        Line incidentalLine3 = new Line(new Point(42, 62), new Point(23, 37));
        Line incidentalLine4 = new Line(new Point(23, 37), new Point(42, 62));
        assertNotNull(incidentalLine3.closestIntersectionToStartOfLine(rect));
        assertNotNull(incidentalLine4.closestIntersectionToStartOfLine(rect));
        assertEquals(50.0, incidentalLine3.closestIntersectionToStartOfLine(rect).getY()); // intersection with the bottom line
        assertEquals(25.0, incidentalLine4.closestIntersectionToStartOfLine(rect).getX()); // intersection with the left line

        Line incidentalLine5 = new Line(new Point(40, 10), new Point(17, 43));
        Line incidentalLine6 = new Line(new Point(17, 43), new Point(40, 10));
        assertNotNull(incidentalLine5.closestIntersectionToStartOfLine(rect));
        assertNotNull(incidentalLine6.closestIntersectionToStartOfLine(rect));
        assertEquals(25, incidentalLine5.closestIntersectionToStartOfLine(rect).getY()); // intersection with the top line
        assertEquals(25, incidentalLine6.closestIntersectionToStartOfLine(rect).getX()); // intersection with the top line

        Line incidentalLine7 = new Line(new Point(40, 10), new Point(49, 100));
        Line incidentalLine8 = new Line(new Point(49, 100), new Point(40, 10));

        assertNotNull(incidentalLine7.closestIntersectionToStartOfLine(rect));
        assertNotNull(incidentalLine8.closestIntersectionToStartOfLine(rect));
        assertEquals(25, incidentalLine7.closestIntersectionToStartOfLine(rect).getY()); // intersection with the top line
        assertEquals(50, incidentalLine8.closestIntersectionToStartOfLine(rect).getY()); // intersection with the bottom line
    }
}
