package objects;

public class Point {

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
            return -1;
        }

        if (this.equals(other)) {
            return 0;
        }

        return truncateToTolerance(Math.sqrt(Math.pow(truncateToTolerance(this.x) - truncateToTolerance(other.x), 2) + Math.pow(truncateToTolerance(this.y) - truncateToTolerance(other.y), 2)));
    }

    // equals -- return true if the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }

        return compareNumbers(this.x, other.x) && compareNumbers(this.y, other.y);
    }

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    private boolean compareNumbers(double a, double b) {
        return truncateToTolerance(Math.abs(truncateToTolerance(a) - truncateToTolerance(b))) <= Constants.TOLERANCE;
    }

    private double truncateToTolerance(double number) {
        double scale = Math.pow(10, 7); // 7 decimal places
        return Math.floor(number * scale) / scale;
    }
}