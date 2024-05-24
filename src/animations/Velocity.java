package animations;

import objects.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // The change in position on the `x` axis
    private double dx;

    // The change in position on the `y` axis
    private double dy;

    /**
     * Constructor for the Velocity class.
     *
     * @param dx The change in position on the `x` axis.
     * @param dy The change in position on the `y` axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point
     * with position (x+dx, y+dy).
     *
     * @param p The point to which the velocity is to be applied.
     * @return A new point with the velocity applied.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Accessor for the `dx` field.
     *
     * @return The change in position on the `x` axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Accessor for the `dy` field.
     *
     * @return The change in position on the `y` axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Creates a new Velocity instance from an angle and speed.
     *
     * @param angle The angle of the velocity in degrees.
     * @param speed The speed of the velocity.
     * @return A new Velocity instance.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Reduce the angle to the domain of [0, 360]
        // This is done due to the inaccuracy of the Math.cos and Math.sin functions
        // for different values for the same angle plus a multiple of 360.
        while (angle >= 360) {
            angle -= 360;
        }

        // TODO: Look at this comment, perhaps modify the code later to allow consistency since the trigonometric functions are inaccurate.
        // In order to allow consistency, I will use the relation sin(x) = cos(x + 180) (maybe handle this later)
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}