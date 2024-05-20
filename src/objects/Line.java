package objects;

public class Line {

    private static final double DEFAULT_POINT = 0;
    private Point start;
    private Point end;

    // Ax + By = C
    private double A;
    private double B;
    private double C;

    // constructors
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

    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
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

    // Returns the start point of the line
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    // Returns the end point of the line
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        Point intersection = calculateIntersectionPoint(other);

        if (intersection == null) {
            if (Math.abs(C - other.C) <= Constants.TOLERANCE) {
                // Check if the lines have common segments:
                // 1. If the start point of 'other' line is in the range of 'this' line.
                // 2. If the end point of 'other' line is in the range of 'this' line.
                return isPointInRange(this.start(), other.start(), this.end()) ||
                        isPointInRange(this.start(), other.end(), this.end());
            }
            return false;
        }

        return isPointOnLine(this, intersection) && isPointOnLine(other, intersection);
    }


    // Returns true if this 2 lines intersect with this line, false otherwise
    public boolean isIntersecting(Line other1, Line other2) {
        if (other1 == null || other2 == null) {
            return false;
        }
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    // return null if there are infinite intersection points.
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

    // equals -- return true if the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return this.start().equals(other.start()) && this.end.equals(other.end()) ||
                this.start().equals(other.end()) && this.end.equals(other.start());
    }

    private double computeAverage(double a, double b) {
        // Don't use intuitive way to calculate the midpoint since it may cause overflow.
        // i.e. (start + end) / 2.
        // Instead, use the following formula: assume (without lose of generality) that a < b so that means:
        // (a - b) / 2 + b = (a - b + 2*b) / 2 = (a + b) / 2 === midPoint!
        // This formula will prevent overflow.
        if (a < b) {
            return ((b - a) / 2) + b;
        }
        if (a > b) {
            return ((a - b) / 2) + a;
        }
        return 0;
    }

    private boolean isInRange(double start, double num, double end) {
        return (start <= num && num <= end) || (end <= num && num <= start);
    }

    private boolean isPointOnLine(Line line, Point point) {
        if (line == null || point == null) {
            return false;
        }
        return isInRange(line.start.getX(), point.getX(), line.end.getX()) &&
                isInRange(line.start.getY(), point.getY(), line.end.getY());
    }

    private void calculateLineEquation() {
        A = end.getY() - start.getY();
        B = start.getX() - end.getX();
        C = A * start.getX() + B * start.getY();
    }

    private boolean isPointInRange(Point point, Point rangeStart, Point rangeEnd) {
        if (point == null || rangeStart == null || rangeEnd == null) {
            return false;
        }
        return isInRange(rangeStart.getX(), point.getX(), rangeEnd.getX()) &&
                isInRange(rangeStart.getY(), point.getY(), rangeEnd.getY());
    }

    private Point calculateIntersectionPoint(Line other) {
        if (other == null) {
            return null;
        }
        // Using Cramer's rule to solve the system of linear equations.
        double determinant = A * other.B - other.A * B;

        // Determinant is zero --> infinite/zero solutions --> the lines coincide/parallel/never collide.
        if (Math.abs(determinant) < Constants.TOLERANCE) {
            return null;
        }

        double x = (other.B * C - B * other.C) / determinant;
        double y = (A * other.C - other.A * C) / determinant;

        return new Point(x, y);
    }
}
