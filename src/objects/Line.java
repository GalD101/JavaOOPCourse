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
        boolean isThisVertical = this.start.getX() == this.end.getX();
        boolean isOtherVertical = other.start.getX() == other.end.getX();
        boolean isVertical = isThisVertical || isOtherVertical;

        boolean isThisHorizontal = this.start.getY() == this.end.getY();
        boolean isOtherHorizontal = other.start.getY() == other.end.getY();
        boolean isHorizontal = isThisHorizontal || isOtherHorizontal;

        if (!isVertical && !isHorizontal) {
            // First, calculate m (slope) and b (intercept)
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
            if (m1 == m2) { // Parallel
                if (b1 == b2) { // Same line
                    return (((isInRange(this.start().getX(), other.start.getX(), this.end.getX()))  &&
                            (isInRange(this.start().getY(), other.start.getY(), this.end.getY())))) ||
                            (((isInRange(this.start.getX(), other.end.getX(), this.end.getX()))     &&
                            (isInRange(this.start.getY(), other.end.getY(), this.end.getY()))));
                }
                return false;
            }
            // Calculate POI and check if it's on both lines segments
            double pointOfIntersectionX = (b2 - b1) / (m1 - m2);
            double pointOfIntersectionY = m1 * pointOfIntersectionX + b1;
            return ((isInRange(this.start.getX(), pointOfIntersectionX, this.end.getX())) &&
                    (isInRange(this.start.getY(), pointOfIntersectionY, this.end.getY())));
        }
        if (isVertical) {
            if (isThisVertical && isOtherVertical) { // Both are vertical
                if ()
            }
        }
    }

    private boolean isInRange(double start, double num, double end) {
        return ((start <= num) && (num <= end)) || ((end <= num) && (num <= start));
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