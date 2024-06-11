package objects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Threshold;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void intersectionPoints() {
//        Rectangle rectangle1 = new Rectangle(new Point(0, 0), 5, 5);
//        Line line = new Line(new Point(-1, -1), new Point(6, 6));
//        assertEquals(2, rectangle1.intersectionPoints(line).size());
//
//        Rectangle rectangle2 = new Rectangle(new Point(25, 50), 20, 25);
//        assertEquals(2, rectangle2.intersectionPoints(line).size());
    }

    @Test
    public void intersectionPointsShouldReturnEmptyListWhenNoIntersections() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 5, 5);
        Line line = new Line(new Point(-1, -1), new Point(-2, -2));
        assertTrue(rectangle.intersectionPoints(line).isEmpty());
    }

    @Test
    public void intersectionPointsShouldReturnOnePointWhenLineTouchesRectangle() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 5, 5);
        Line line = new Line(new Point(0, 0), new Point(-2, -2));
        assertEquals(1, rectangle.intersectionPoints(line).size());
        assertTrue(new Point(0, 0).equals(rectangle.intersectionPoints(line).get(0)));
    }

    @Test
    public void intersectionPointsShouldReturnTwoPointsWhenLineCrossesRectangle() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 5, 5);
        Line line = new Line(new Point(-1, 2.5), new Point(6, 2.5));
        assertEquals(2, rectangle.intersectionPoints(line).size());
    }

    @Test
    void getWidth() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 5.5, 11.8);
        assertEquals(5.5, rectangle.getWidth());
    }

    @Test
    void getHeight() {
        Rectangle rectangle = new Rectangle(new Point(26, 89), 5.5, 11.8);
        assertEquals(11.8, rectangle.getHeight(), Threshold.TOLERANCE);
    }

    @Test
    void getUpperLeft() {
        Rectangle rectangle = new Rectangle(new Point(92, 77), 18.1, 20.23);
        assertEquals(92, rectangle.getUpperLeft().getX());
        assertEquals(77, rectangle.getUpperLeft().getY());
    }

    @Test
    void getLowerRight() {
        Rectangle rectangle = new Rectangle(new Point(220.5, 100.10), 55.0, 99.3);
        assertEquals(275.5, rectangle.getLowerRight().getX());
        assertEquals(199.4, rectangle.getLowerRight().getY(), Threshold.TOLERANCE);
    }

    @Test
    void getTopLine() {
        Rectangle rectangle = new Rectangle(new Point(82.666, 73.101), 25.44, 11.92);
        assertEquals(82.666, rectangle.getTopLine().start().getX());
        assertEquals(73.101, rectangle.getTopLine().start().getY());
        assertEquals(108.106, rectangle.getTopLine().end().getX());
        assertEquals(73.101, rectangle.getTopLine().end().getY());
    }

    @Test
    void getBottomLine() {
        Rectangle rectangle = new Rectangle(new Point(55, 68), 16.77, 91.0);
        assertEquals(55, rectangle.getBottomLine().start().getX());
        assertEquals(159, rectangle.getBottomLine().start().getY());
        assertEquals(71.77, rectangle.getBottomLine().end().getX());
        assertEquals(159, rectangle.getBottomLine().end().getY());
    }

    @Test
    void getLeftLine() {
        Rectangle rectangle = new Rectangle(new Point(100, 250), 50, 25);
        assertEquals(100, rectangle.getLeftLine().start().getX());
        assertEquals(250, rectangle.getLeftLine().start().getY());
        assertEquals(100, rectangle.getLeftLine().end().getX());
        assertEquals(275, rectangle.getLeftLine().end().getY());
    }

    @Test
    void getRightLine() {
        Rectangle rectangle = new Rectangle(new Point(203.4, 162.2), 23.6, 3.7);
        assertEquals(227.0, rectangle.getRightLine().start().getX());
        assertEquals(162.2, rectangle.getRightLine().start().getY());
        assertEquals(227.0, rectangle.getRightLine().end().getX());
        assertEquals(165.9, rectangle.getRightLine().end().getY(), Threshold.TOLERANCE);
    }

    @Test
    @Disabled("no need to test that now")
    void generateRandomPointInside() {
    }

    @Test
    void constructorShouldCreateRectangleWithCorrectDimensions() {
        Point upperLeft = new Point(0, 0);
        Rectangle rectangle = new Rectangle(upperLeft, 5, 10);
        assertEquals(5, rectangle.getWidth());
        assertEquals(10, rectangle.getHeight());
    }

    @Test
    void constructorShouldThrowExceptionWhenUpperLeftIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(null, 5, 10);
        });
    }

    @Test
    public void constructorShouldThrowExceptionWhenWidthIsNonPositive() {
        Point upperLeft = new Point(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(upperLeft, -5, 10);
        });
    }

    @Test
    public void constructorShouldThrowExceptionWhenHeightIsNonPositive() {
        Point upperLeft = new Point(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(upperLeft, 5, -10);
        });
    }

    @Test
    public void constructorShouldThrowExceptionWhenUpperLeftHasNegativeX() {
        Point upperLeft = new Point(-1, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(upperLeft, 5, 10);
        });
    }

    @Test
    public void constructorShouldThrowExceptionWhenUpperLeftHasNegativeY() {
        Point upperLeft = new Point(0, -1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(upperLeft, 5, 10);
        });
    }
}