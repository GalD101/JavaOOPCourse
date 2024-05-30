package objects;

import utils.Threshold;

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
        boolean startIsNull = start == null;
        boolean endIsNull = end == null;
        double startX = startIsNull ? DEFAULT_POINT : start.getX();
        double startY = startIsNull ? DEFAULT_POINT : start.getY();
        double endX = endIsNull ? DEFAULT_POINT : end.getX();
        double endY = endIsNull ? DEFAULT_POINT : end.getY();

        this.start = new Point(startX, startY);
        this.end = new Point(endX, endY);
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
        return this.start.distance(this.end);
    }

    /**
     * Calculates and returns the middle point of the line.
     * If the start and end points are the same, returns the start point.
     * If either the start or end point is null, returns null.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        // TODO: Remove this check in case it really is redundant
        if (this.start == null || this.end == null) {
            return null;
        }
        double midX;
        double midY;
        if (this.start.equals(this.end)) {
            return new Point(this.start.getX(), this.start.getY());
        }
        midX = computeAverage(this.start.getX(), this.end.getX());
        midY = computeAverage(this.start.getY(), this.end.getY());

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

        return isPointOnLine(this, intersection) && isPointOnLine(other, intersection);
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

        if (intersection == null || !isPointOnLine(this, intersection) || !isPointOnLine(other, intersection)) {
            return null;
        }

        return intersection;
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
        return this.start().equals(other.start()) && this.end.equals(other.end())
                || this.start().equals(other.end()) && this.end.equals(other.start());
    }

    /**
     * Calculates the average of two numbers in a way that prevents overflow.
     * If a > b, calculates (a - b) / 2 + b.
     * If a < b, calculates (b - a) / 2 + a.
     * If a = b, returns a.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The average of the two numbers.
     */
    private double computeAverage(double a, double b) {
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            // Numbers have different signs,
            // therefore, (a + b) / 2 will prevent overflow.
            return (a + b) / 2;
        }
        // Numbers have the same sign,
        // therefore, (a - b) / 2 + b will prevent overflow.
        // Use the following formula: assume (without lose of generality) that a > b so that means:
        // (a - b) / 2 + b = (a - b + 2*b) / 2 = (a + b) / 2 === midPoint!
        // This formula will prevent overflow.
        if (a >= b) {
            return ((a - b) / 2) + b;
        }
        return ((b - a) / 2) + a;
    }

    /**
     * Checks if a number is in the range between two other numbers.
     * The comparison is done after truncating each number
     * to a certain precision using the truncateToTolerance method.
     * The range is considered to be inclusive, and a tolerance is added to the range to account for rounding errors.
     *
     * @param val1 The start of the range.
     * @param num  The number to check.
     * @param val2 The end of the range.
     * @return true if the number is in the range, false otherwise.
     */
    private boolean isInRange(double val1, double num, double val2) {
        return ((val1 - Threshold.TOLERANCE <= num
                && num <= val2 + Threshold.TOLERANCE)
                || (val2 - Threshold.TOLERANCE <= num
                && num <= val1 + Threshold.TOLERANCE));
    }

    /**
     * Checks if a point is on a line.
     * If the line or the point is null, returns false.
     * Checks if the x and y coordinates of the point are in the range
     * between the x and y coordinates of the start and end points of the line.
     *
     * @param line  The line to check.
     * @param point The point to check.
     * @return true if the point is on the line, false otherwise.
     */
    private boolean isPointOnLine(Line line, Point point) {
        if (line == null || point == null) {
            return false;
        }
        return isInRange(line.start.getX(), point.getX(), line.end.getX())
                && isInRange(line.start.getY(), point.getY(), line.end.getY());
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
        a = end.getY() - start.getY();
        b = start.getX() - end.getX();
        c = a * start.getX() + b * start.getY();
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

    public boolean isVertical() {
        return Math.abs(this.start.getX() - this.end.getX()) <= Threshold.TOLERANCE;
    }

    public boolean isParallel(Line other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.a * other.b - other.a * this.b) <= Threshold.TOLERANCE;
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

        // Check for common edge point
        if ((this.start().equals(other.start())) || (this.start().equals(other.end()))) {
            return this.start();
        }
        if ((this.end().equals(other.start())) || (this.end().equals(other.end()))) {
            return this.end();
        }
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
