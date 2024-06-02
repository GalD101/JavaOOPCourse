// 322558297 Gal Dali
package objects;

import java.awt.Color;
import java.util.Random;

import utils.RandomSingleton;

/**
 * This class represents a Rectangle object with various properties and methods.
 * The Rectangle object is defined by its upper left and lower right points and a color.
 */
public class Rectangle {
    private Point upperLeft;
    private Point lowerRight;
    private Line topLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;
    private Color color;

    /**
     * Constructs a rectangle object with the given upper left and lower right points.
     *
     * @param upperLeft  The upper left point of the rectangle.
     * @param lowerRight The lower right point of the rectangle.
     * @param color      The color of the rectangle.
     */
    public Rectangle(Point upperLeft, Point lowerRight, Color color) {
        if (upperLeft == null || lowerRight == null) {
            throw new IllegalArgumentException("The upper left and lower right points must not be null.");
        }
        if (upperLeft.getX() > lowerRight.getX() || upperLeft.getY() > lowerRight.getY()) {
            throw new IllegalArgumentException("The upper left point must be"
                    + "above and to the left of the lower right point.");
        }
        if (upperLeft.equals(lowerRight)) {
            throw new IllegalArgumentException("The upper left and lower right points must not be the same.");
        }
        if (upperLeft.getX() == lowerRight.getX() || upperLeft.getY() == lowerRight.getY()) {
            throw new IllegalArgumentException("The rectangle must have a positive area.");
        }
        if (upperLeft.getX() < 0 || upperLeft.getY() < 0 || lowerRight.getX() < 0 || lowerRight.getY() < 0) {
            throw new IllegalArgumentException("The rectangle must have non-negative coordinates.");
        }
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.lowerRight = new Point(lowerRight.getX(), lowerRight.getY());
        this.color = color == null ? Color.BLACK : color;
    }

    /**
     * Constructs a rectangle object with the given upper left and lower right points.
     * The color of the rectangle will be set to null. It will get the default color
     *
     * @param upperLeft  The upper left point of the rectangle.
     * @param lowerRight The lower right point of the rectangle.
     */
    public Rectangle(Point upperLeft, Point lowerRight) {
        this(upperLeft, lowerRight, null);
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
     * Returns the color of the rectangle.
     * If the color of the rectangle is null, it returns black.
     *
     * @return The color of the rectangle.
     */
    public Color getColor() {
        if (this.color == null) {
            return Color.BLACK;
        }
        return this.color;
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
