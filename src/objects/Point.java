package objects;

public class Point {
    private static final double TOLERANCE = 0.0000001;

    private double x;
    private double y;

    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        if (other == null) {
            throw new IllegalArgumentException("Other point cannot be null");
//            return 0;
//            return -1;
        }

        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    // equals -- return true if the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }

        // tolerance = 0.0000001
        return compareNumbers(this.x,other.x) && compareNumbers(this.y, other.y);
    }

    private boolean compareNumbers(double a, double b) {
        return Math.abs(a - b) <= TOLERANCE;
    }
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}