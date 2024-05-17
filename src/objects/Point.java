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
            throw new IllegalArgumentException("Other point cannot be null");
//            return 0;
        }

        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    // equals -- return true if the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
    }

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}