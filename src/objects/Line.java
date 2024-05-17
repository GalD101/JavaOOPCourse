package objects;

public class Line {
    private Point start;
    private Point end;

    // constructors
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double midX;
        double midY;

        midX = (this.start.getX() + this.end.getX()) / 2;
        midY = (this.start.getY() + this.end.getY()) / 2;
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

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        boolean isVertical = false;
        boolean isHorizontal = false; // Maybe use this later
        // First, calculate m (slope) and b (intercept)
//        if (...) {
//            // TODO: handle case of vertical/horizontal line
//        }

        double m1 = this.calculateSlope();
        double m2 = other.calculateSlope();
        double b1 = this.calculateIntercept();
        double b2 = other.calculateIntercept();

        double s1X = this.start.getX();
        double s1Y = this.start.getY();
        double s2X = other.start.getX();
        double s2Y = other.start.getY();


        double e1X = this.end.getX();
        double e1Y = this.end.getY();
        double e2X = other.end.getX();
        double e2Y = other.end.getY();

        // TODO Make this look better and enhance calculations (use a function)
        if ((m1 == m2) && (b1 == b2)) { // same line - need to check if lines overlap
            if (((s1X < s2X) && (s2X < e1X)) && ((s1Y < s2Y) && (s2Y < e1Y))) { // check out 'notes and edge cases', start and end are commutative
                // infinite intersections
                return true;
            }

            if ((s2X == e1X) && (s2Y == e1Y)) {
                // one intersection (at point s1 or e2)
                return true;
            }

            if (((s1X < e2X) && (e2X < e1X)) && ((s1Y < e2Y) && (e2Y < e1Y))) {
                // infinite intersections
                return true;
            }

            if ((e2X == s1X) && (e2Y == s1Y)) {
                // one intersection
                return true;
            }

            return false;
        }
        return false;
    }

    private double calculateSlope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    private double calculateIntercept() {
        // y = m(x - P_x) + P_y -> y = mx + (P_y - mP_x) -> y = mx + b -> b = P_y - mP_x
        return this.start.getY() - this.calculateSlope() * this.start.getX(); // Should be the same as this.end.getY() - this.calculateSlope() * this.end.getX();
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }


        return null;
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return false;
    }

}