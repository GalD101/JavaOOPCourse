// 322558297 Gal Dali
package objects;

import static utils.Threshold.compareNumbers;

/**
 * The Point class represents a point in a two-dimensional space.
 * Each point is defined by its x and y coordinates.
 * The class provides methods to get the x and y coordinates, calculate the distance to another point,
 * and check if two points are equal within a certain threshold.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a Point object with the given x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other The other point to which the distance is to be calculated.
     *              If the other point is null, the method will return -1.
     * @return The distance between this point and the other point.
     * If the other point is the same as this point, the method will return 0.
     */
    public double distance(Point other) {
        if (other == null) {
            return -1;
        }

        if (this.equals(other)) {
            return 0;
        }

        double dx = this.x - other.x;
        double dy = this.y - other.y;

        // If one wants to improve accuracy and precision for far-flung points,
        // one can use the hypot method from the Math class instead; i.e. distance = Math.hypot(dx, dy).
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point with which to compare. If the other point is null, the method will return false.
     * @return true if the x and y coordinates of this point and the other point
     * are equal within the tolerance defined in the Threshold class, false otherwise.
     * The comparison is done by the method compareNumbers which compares two numbers
     * and returns true if they are equal within the tolerance, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }

        return compareNumbers(this.x, other.x) && compareNumbers(this.y, other.y);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x The new x-coordinate of this point.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y The new y-coordinate of this point.
     */
    public void setY(double y) {
        this.y = y;
    }
}