// 322558297 Gal Dali

package objects;

import utils.Threshold;

import static utils.MathUtils.computeAverage;
import static utils.Threshold.isInRange;

/**
 * The Line class represents a line in a two-dimensional space.
 * Each line is defined by its start and end points, represented by Point objects.
 * The class provides methods to calculate the length of the line, find the middle point of the line,
 * check if the line intersects with another line, find the intersection point with another line,
 * and check if two lines are equal.
 */
public class Line {

    private static final double DEFAULT_POINT = 0;
    private Point start;
    private Point end;

    // ax + by = c
    private double a;
    private double b;
    private double c;

    /**
     * Constructor for Line class.
     * Initializes the start and end points of the line.
     * If the start or end point is null, it is initialized to (0,0).
     * Also calculates the line equation coefficients A, B and C.
     *
     * @param start The start point of the line.
     * @param end   The end point of the line.
     */
    public Line(Point start, Point end) {
        if (start == null || end == null) {
            this.start = new Point(DEFAULT_POINT, DEFAULT_POINT);
        } else {
            this.start = new Point(start.getX(), start.getY());
            this.end = new Point(end.getX(), end.getY());
        }

        calculateLineEquation();
    }

    /**
     * Constructor for Line class.
     * Initializes the start and end points of the line using the provided coordinates.
     * Also calculates the line equation coefficients A, B and C.
     *
     * @param x1 The x-coordinate of the start point.
     * @param y1 The y-coordinate of the start point.
     * @param x2 The x-coordinate of the end point.
     * @param y2 The y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Calculates and returns the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * Calculates and returns the middle point of the line.
     * If the start and end points are the same, returns the start point.
     * If either the start or end point is null, returns null.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        if (this.start == null || this.end == null) {
            return null;
        }

        if (this.start().equals(this.end())) {
            return new Point(this.start().getX(), this.start().getY());
        }

        double midX;
        double midY;
        midX = computeAverage(this.start().getX(), this.end().getX());
        midY = computeAverage(this.start().getY(), this.end().getY());

        return new Point(midX, midY);
    }

    /**
     * Returns a new Point object with the same coordinates as the start point of the line.
     *
     * @return The start point of the line.
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns a new Point object with the same coordinates as the end point of the line.
     *
     * @return The end point of the line.
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Checks if the line intersects with another line.
     * If the other line is null, returns false.
     * If the lines intersect at a single point, returns true.
     * If the lines coincide or are parallel, checks if they have a common segment and returns true if they do.
     *
     * @param other The other line to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        Point intersection = calculateIntersectionPoint(other);

        if (intersection == null) {
            if (Math.abs(c - other.c) <= Threshold.TOLERANCE) {
                // Check if the lines have common segments:
                // 1. If the start point of 'other' line is in the range of 'this' line.
                // 2. If the end point of 'other' line is in the range of 'this' line.
                return isPointInRange(this.start(), other.start(), this.end())
                        || isPointInRange(this.start(), other.end(), this.end());
            }
            return false;
        }

        return this.isPointOnLine(intersection) && other.isPointOnLine(intersection);
    }

    /**
     * Checks if the line intersects with two other lines.
     * If either of the other lines is null, returns false.
     * Returns true if the line intersects with both other lines.
     *
     * @param other1 The first other line to check for intersection.
     * @param other2 The second other line to check for intersection.
     * @return true if the line intersects with both other lines, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Calculates and returns the intersection point of the line with another line.
     * If the other line is null, returns null.
     * If the lines do not intersect, returns null.
     * If the lines coincide or are parallel, returns null.
     *
     * @param other The other line to calculate the intersection point with.
     * @return The intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        Point intersection = calculateIntersectionPoint(other);

        if (intersection == null || !(this.isPointOnLine(intersection) && other.isPointOnLine(intersection))) {
            return null;
        }

        return intersection;
    }

    /**
     * Returns the closest intersection point between this line and a given rectangle.
     *
     * <p>This method first checks if the line intersects with the rectangle
     * by calling the rectangle's intersectionPoints method.
     * If there are no intersection points, the method returns null.
     * If there are intersection points,
     * the method calculates the distance from the start of the line to each intersection point.
     * It then returns the intersection point that is closest to the start of the line.
     *
     * @param rect The rectangle to check for intersections with. Must not be null.
     * @return The closest intersection point to the start of the line,
     * or null if the line does not intersect with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect == null) {
            return null;
        }

        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        int minPointDistanceIndex = 0;
        for (int i = 0; i < intersectionPoints.size(); i++) {
            double currentDistanceFromStart = intersectionPoints.get(i).distance(this.start());
            double minDistanceFromStart = intersectionPoints.get(minPointDistanceIndex).distance(this.start());
            if (currentDistanceFromStart < minDistanceFromStart) {
                minPointDistanceIndex = i;
            }
        }
        return intersectionPoints.get(minPointDistanceIndex);
    }

    /**
     * Checks if the line is equal to another line.
     * Two lines are considered equal if they have the same start and end points,
     * or if one line's start point is the other line's end point and vice versa.
     * If the other line is null, returns false.
     *
     * @param other The other line to check for equality.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return this.start().equals(other.start()) && this.end().equals(other.end())
                || this.start().equals(other.end()) && this.end().equals(other.start());
    }


    /**
     * Checks if a point is on a line.
     * If the line or the point is null, returns false.
     * Checks if the x and y coordinates of the point are in the range
     * between the x and y coordinates of the start and end points of the line.
     *
     * @param point The point to check.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isPointOnLine(Point point) {
        if (point == null) {
            return false;
        }
        return isInRange(this.start().getX(), point.getX(), this.end().getX())
                && isInRange(this.start().getY(), point.getY(), this.end().getY());
    }

    /**
     * Calculates the coefficients a, b and c of the line equation
     * ax + by = c using the start and end points of the line.
     * 'a' is calculated as the difference in y-coordinates of the end and start points.
     * 'b' is calculated as the difference in x-coordinates of the start and end points.
     * 'c' is calculated as 'a' times the x-coordinate of the start point
     * plus 'b' times the y-coordinate of the start point.
     */
    private void calculateLineEquation() {
        a = this.end().getY() - this.start().getY();
        b = this.start().getX() - this.end().getX();
        c = a * this.start().getX() + b * this.start().getY();
    }

    /**
     * Checks if a point is in the range between two other points.
     * If any of the points is null, returns false.
     * Checks if the x and y coordinates of the point are in the range
     * between the x and y coordinates of the start and end points.
     *
     * @param point      The point to check.
     * @param rangeStart The start of the range.
     * @param rangeEnd   The end of the range.
     * @return true if the point is in the range, false otherwise.
     */
    private boolean isPointInRange(Point point, Point rangeStart, Point rangeEnd) {
        if (point == null || rangeStart == null || rangeEnd == null) {
            return false;
        }
        return isInRange(rangeStart.getX(), point.getX(), rangeEnd.getX())
                && isInRange(rangeStart.getY(), point.getY(), rangeEnd.getY());
    }

    /**
     * Calculates the intersection point of the line with another line using Cramer's rule.
     * If the other line is null, returns null.
     * If the lines have a common edge point, returns that point.
     * If the determinant of the system of linear equations is zero
     * (indicating that the lines coincide, are parallel, or never collide), returns null.
     * Otherwise, calculates the x and y coordinates
     * of the intersection point and returns a new Point object with those coordinates.
     *
     * @param other The other line to calculate the intersection point with.
     * @return The intersection point if the lines intersect, null otherwise.
     */
    private Point calculateIntersectionPoint(Line other) {
        if (other == null) {
            return null;
        }

//        // Check for common edge point
//        // FIXME: This causes an unexpected issue when I have two lines that have the same end and are parallel
//        if ((this.start().equals(other.start())) || (this.start().equals(other.end()))) {
//            return this.start();
//        }
//        if ((this.end().equals(other.start())) || (this.end().equals(other.end()))) {
//            return this.end();
//        }
        // Using Cramer's rule to solve the system of linear equations.
        double determinant = a * other.b - other.a * b;

        // Determinant is zero --> infinite/zero solutions --> the lines coincide/parallel/never collide.
        if (Math.abs(determinant) < Threshold.TOLERANCE) {
            return null;
        }

        double x = (other.b * c - b * other.c) / determinant;
        double y = (a * other.c - other.a * c) / determinant;

        return new Point(x, y);
    }
}
