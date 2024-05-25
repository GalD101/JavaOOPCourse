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
        while (angle >= 360.0) {
            angle -= 360.0;
        }

        // NOTE: There are slight inaccuracies in the Math.cos and Math.sin functions.
        // (the identity cos(90-x)= sin(x) is not exact here but will almost be equal).
        // This won't matter for the purposes of this project
        // since the inaccuracies are very small, and we use a threshold regardless.
        // The inaccuracies grow larger for larger angles.
        // This is another reason why I will reduce the angle to the domain of [0, 360].
        boolean isCosineZero = (angle == 90.0 || angle == 270.0);
        boolean isSineZero = (angle == 0.0 || angle == 180.0);
        double dx = isCosineZero ? 0 : Math.cos(Math.toRadians(angle)) * speed;
        double dy = isSineZero ? 0 : Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}