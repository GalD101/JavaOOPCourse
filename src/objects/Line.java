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

    private Point calculateIntersectionPoint(Line other) {
        double m1 = this.calculateSlope();
        double b1 = this.calculateIntercept();

        double m2 = other.calculateSlope();
        double b2 = other.calculateIntercept();

        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;

        return new Point(x, y);
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        boolean isThisVertical = this.start.getX() == this.end.getX();
        boolean isOtherVertical = other.start.getX() == other.end.getX();
        boolean isVertical = isThisVertical || isOtherVertical;

        if (!isVertical) {
            // First, calculate m (slope) and b (intercept)
            double m1 = this.calculateSlope();
            double m2 = other.calculateSlope();
            double b1 = this.calculateIntercept();
            double b2 = other.calculateIntercept();

            // TODO Make this look better and enhance calculations (use a function)
            if (m1 == m2) { // Parallel
                if (b1 == b2) { // Same line
                    return (((isInRange(this.start().getX(), other.start.getX(), this.end.getX())) &&
                            (isInRange(this.start().getY(), other.start.getY(), this.end.getY())))) ||
                            (((isInRange(this.start.getX(), other.end.getX(), this.end.getX())) &&
                                    (isInRange(this.start.getY(), other.end.getY(), this.end.getY()))));
                }
                return false;
            }

            // Calculate POI and check if it's on both lines segments
            Point pointOfIntersection = this.calculateIntersectionPoint(other);
            return ((isInRange(this.start.getX(), pointOfIntersection.getX(), this.end.getX())) &&
                    (isInRange(this.start.getY(), pointOfIntersection.getY(), this.end.getY())));
        }

        if (isThisVertical && isOtherVertical) { // Both are vertical
            if ((this.start.getX() == other.start.getX())) { // Same line, check y range
                return isInRange(this.start.getY(), other.start().getY(), this.end.getY());
            }

            return false;
        }

        // TODO: Change this! you are using the trinary operator too many times!
        if ((isThisVertical && !isOtherVertical) || (!isThisVertical && isOtherVertical)) { // One is vertical, the other is not
            double pointOfIntersectionX = isThisVertical ? this.start.getX() : other.end.getX();
            double m = isThisVertical ? other.calculateSlope() : this.calculateSlope();
            double b = isThisVertical ? other.calculateIntercept() : this.calculateIntercept();
            double pointOfIntersectionY = isThisVertical ? m * other.end.getY() + b : m * this.end.getY() + b;

            return (((isInRange(this.start.getX(), pointOfIntersectionX, this.end.getX())) &&
                    (isInRange(this.start.getY(), pointOfIntersectionY, this.end.getY()))) &&
                    ((isInRange(other.start.getX(), pointOfIntersectionX, other.end.getX())) &&
                            (isInRange(other.start.getY(), pointOfIntersectionY, other.end.getY()))));
        }

        return false;
    }

    private boolean isInRange(double start, double num, double end) {
        if (end < start) {
            return ((end <= num) && (num <= start)) || ((start <= num) && (num <= end));
        }
        return ((start <= num) && (num <= end)) || ((end <= num) && (num <= start));
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // Lines intersect!


        return null;
    }

    // NOTE!!! This function assumes input of lines that are NOT vertical or horizontal!!!! (there is no check for this case)
    private double calculateSlope() {
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();

        return (endY - startY) / (endX - startX);
    }

    private double calculateIntercept() {
        // y = m(x - P_x) + P_y -> y = mx + (P_y - mP_x) -> y = mx + b -> b = P_y - mP_x
        return this.start.getY() - this.calculateSlope() * this.start.getX(); // Should be the same as this.end.getY() - this.calculateSlope() * this.end.getX();
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end()) ||
                this.start.equals(other.end()) && this.end.equals(other.start());
    }
}