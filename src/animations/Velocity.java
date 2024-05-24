package animations;

import objects.Point;

// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx; // the change in position on the `x` axis
    private double dy; // the change in position on the `y` axis

    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    // accessors
    public double getDx() {
        return this.dx;
    }

    public double getDy() {
        return this.dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}