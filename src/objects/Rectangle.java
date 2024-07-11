// 322558297 Gal Dali

package objects;

import java.util.Random;

import utils.RandomSingleton;

/**
 * This class represents a Rectangle object with various properties and methods.
 * The Rectangle object is defined by its upper left and lower right points.
 */
public class Rectangle {
    private Point upperLeft;
    private Point lowerRight;

    /**
     * Constructs a rectangle object with the given upper left and lower right points.
     *
     * @param upperLeft  The upper left point of the rectangle.
     * @param lowerRight The lower right point of the rectangle.
     */
    public Rectangle(Point upperLeft, Point lowerRight) {
        if (upperLeft == null || lowerRight == null) {
            throw new IllegalArgumentException("The upper left and lower right points must not be null.");
        }
        if (upperLeft.getX() > lowerRight.getX() || upperLeft.getY() > lowerRight.getY()) {
            throw new IllegalArgumentException("The upper left point must be"
                    + " above and to the left of the lower right point.");
        }
        if (upperLeft.equals(lowerRight)) {
            throw new IllegalArgumentException("The upper left and lower right points must not be the same.");
        }
        if (upperLeft.getX() == lowerRight.getX() || upperLeft.getY() == lowerRight.getY()) {
            throw new IllegalArgumentException("The rectangle must have a positive area.");
        }
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.lowerRight = new Point(lowerRight.getX(), lowerRight.getY());
    }

    /**
     * Constructs a Rectangle object with the given upper left point, width, and height.
     *
     * @param upperLeft The upper left point of the rectangle. Must not be null.
     * @param width     The width of the rectangle. Must be positive.
     * @param height    The height of the rectangle. Must be positive.
     * @throws IllegalArgumentException if the upper left point is null,
     *                                  or if the width or height is less than or equal to 0,
     *                                  or if the x or y coordinate of the upper left point is negative.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        // Check if the upper left point is null
        if (upperLeft == null) {
            throw new IllegalArgumentException("The upper left point must not be null.");
        }
        // Check if the width or height is less than or equal to 0
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The rectangle must have a positive area.");
        }
        // Check if the x or y coordinate of the upper left point is negative
        if (upperLeft.getX() < 0 || upperLeft.getY() < 0) {
            throw new IllegalArgumentException("The rectangle must have non-negative coordinates.");
        }

        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Calculates the intersection points between this rectangle and a given line.
     *
     * @param line The line to check for intersections with. Must not be null.
     * @return A list of intersection points. If no intersection points are found, the list will be empty.
     * @throws IllegalArgumentException if the line is null.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new java.util.ArrayList<>();
        Line[] rectangleLines = {this.getTopLine(), this.getBottomLine(), this.getLeftLine(), this.getRightLine()};
        for (Line rectangleLine : rectangleLines) {
            Point currentIntersectionPoint = rectangleLine.intersectionWith(line);
            if (currentIntersectionPoint != null) {
                // There is an intersection point!
                if (intersectionPoints.isEmpty()) {
                    intersectionPoints.add(currentIntersectionPoint);
                } else {
                    for (int i = 0; i < intersectionPoints.size(); i++) {
                        // Before adding the point, make sure we didn't encounter that point already (edges)
                        if (!currentIntersectionPoint.equals(intersectionPoints.get(i))) {
                            intersectionPoints.add(currentIntersectionPoint);
                        }
                    }
                }
            }
        }

        if (intersectionPoints.isEmpty()) {
            Point leftSideMid = new Point(utils.MathUtils.computeAverage(
                    this.getLeftLine().start().getX(), this.getLeftLine().end().getX()),
                    utils.MathUtils.computeAverage(
                            this.getLeftLine().start().getY(), this.getLeftLine().end().getY()));
            Point rightSideMid = new Point(utils.MathUtils.computeAverage(
                    this.getRightLine().start().getX(), this.getRightLine().end().getX()),
                    utils.MathUtils.computeAverage(
                            this.getRightLine().start().getY(), this.getRightLine().end().getY()));
            if (this.isPointInside(line.start())) {
                if (line.start().distance(leftSideMid) < line.start().distance(this.getRightLine().start())) {
                    intersectionPoints.add(leftSideMid);
                } else {
                    intersectionPoints.add(rightSideMid);
                }
            }
        }
        return intersectionPoints;
    }

    /**
     * Checks if a given point is inside the rectangle.
     *
     * <p>This method checks if the x-coordinate of the point is greater than or equal to
     * the x-coordinate of the upper left point of the rectangle
     * and less than or equal to the x-coordinate of the lower right point of the rectangle.
     * It also checks if the y-coordinate of the point is greater than or equal to
     * the y-coordinate of the upper left point of the rectangle
     * and less than or equal to the y-coordinate of the lower right point of the rectangle.
     * If both conditions are met, the point is considered to be inside the rectangle.
     *
     * @param point The point to check. Must not be null.
     * @return true if the point is inside the rectangle, false otherwise.
     * @throws IllegalArgumentException if the point is null.
     */
    private boolean isPointInside(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("The point must not be null.");
        }
        return point.getX() >= this.getUpperLeft().getX() && point.getX() <= this.getLowerRight().getX()
                && point.getY() >= this.getUpperLeft().getY() && point.getY() <= this.getLowerRight().getY();
    }

    /**
     * Returns the width of the rectangle.
     * The width is calculated as the difference between the x-coordinates of the lower right and upper left points.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.getLowerRight().getX() - this.getUpperLeft().getX();
    }

    /**
     * Returns the height of the rectangle.
     * The height is calculated as the difference between the y-coordinates of the lower right and upper left points.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.getLowerRight().getY() - this.getUpperLeft().getY();
    }

    /**
     * Returns a new Point object that represents the upper left point of the rectangle.
     * The new Point object has the same x and y coordinates as the upper left point of the rectangle.
     *
     * @return A new Point object that represents the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * Returns a new Point object that represents the lower right point of the rectangle.
     * The new Point object has the same x and y coordinates as the lower right point of the rectangle.
     *
     * @return A new Point object that represents the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.lowerRight.getX(), this.lowerRight.getY());
    }

    /**
     * Returns a Line object that represents the top line of the rectangle.
     * The top line is defined by the upper left point of the rectangle and a new point with the
     * x-coordinate of the lower right point and the y-coordinate of the upper left point.
     *
     * @return A Line object that represents the top line of the rectangle.
     */
    public Line getTopLine() {
        return new Line(this.getUpperLeft(), new Point(this.getLowerRight().getX(), this.getUpperLeft().getY()));
    }

    /**
     * Returns a Line object that represents the bottom line of the rectangle.
     * The bottom line is defined by a new point with the x-coordinate of the upper left point
     * and the y-coordinate of the lower right point, and the lower right point of the rectangle.
     *
     * @return A Line object that represents the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return new Line(new Point(this.getUpperLeft().getX(), this.getLowerRight().getY()), this.getLowerRight());
    }

    /**
     * Returns a Line object that represents the left line of the rectangle.
     * The left line is defined by the upper left point of the rectangle and a new point with the
     * x-coordinate of the upper left point and the y-coordinate of the lower right point.
     *
     * @return A Line object that represents the left line of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX(), this.getLowerRight().getY()));
    }

    /**
     * Returns a Line object that represents the right line of the rectangle.
     * The right line is defined by a new point with the x-coordinate of the lower right point
     * and the y-coordinate of the upper left point, and the lower right point of the rectangle.
     *
     * @return A Line object that represents the right line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(new Point(this.getLowerRight().getX(), this.getUpperLeft().getY()), this.getLowerRight());
    }

    /**
     * Generates a random point inside the rectangle with a given boundary off the walls of the rectangle.
     * The x-coordinate of the point is generated as a random double between the x-coordinate of the
     * upper left point plus the boundary and the x-coordinate of the lower right point minus the boundary.
     * The y-coordinate of the point is generated as a random double between the y-coordinate of the
     * upper left point plus the boundary and the y-coordinate of the lower right point minus the boundary.
     *
     * @param boundary The boundary off the walls of the rectangle.
     * @return A new Point object that represents the randomly generated point inside the rectangle.
     */
    public Point generateRandomPointInside(double boundary) {
        Random rand = RandomSingleton.getInstance();
        double randomX = rand.nextDouble() * (this.getWidth() - 2 * boundary) + this.getUpperLeft().getX() + boundary;
        double randomY = rand.nextDouble() * (this.getHeight() - 2 * boundary) + this.getUpperLeft().getY() + boundary;
        return new Point(randomX, randomY);
    }
}
