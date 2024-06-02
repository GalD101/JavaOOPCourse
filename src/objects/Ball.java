// 322558297 Gal Dali
package objects;

import animations.Velocity;
import biuoop.DrawSurface;
import utils.Threshold;

/**
 * Ball class represents a ball object in a 2D space.
 * It has properties like center, radius, color and velocity.
 */
public class Ball {
    private Point center; // The center point of the ball
    private int r; // The radius of the ball
    private java.awt.Color color; // The color of the ball
    private Velocity velocity; // The velocity of the ball

    /**
     * Constructor that initializes a new Ball object with a center point, radius and color.
     *
     * @param center The center point of the ball
     * @param r      The radius of the ball
     * @param color  The color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = (center == null) ? new Point(0, 0) : new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructor that initializes a new Ball object with a center point (x, y coordinates), radius and color.
     *
     * @param centerX The x-coordinate of the center point of the ball
     * @param centerY The y-coordinate of the center point of the ball
     * @param r       The radius of the ball
     * @param color   The color of the ball
     */
    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this(new Point(centerX, centerY), r, color);
    }

    /**
     * Returns the x-coordinate of the center of the ball.
     *
     * @return The x-coordinate of the center of the ball
     */
    public int getX() {
        if (this.center == null) {
            throw new NullPointerException("Center point of the ball is not initialized.");
        }
        return (int) Math.round(this.center.getX());
    }

    /**
     * Returns the y-coordinate of the center of the ball.
     *
     * @return The y-coordinate of the center of the ball
     */
    public int getY() {
        if (this.center == null) {
            throw new NullPointerException("Center point of the ball is not initialized.");
        }
        return (int) Math.round(this.center.getY());
    }

    /**
     * Returns the radius of the ball.
     *
     * @return The radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return The color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return The velocity of the ball
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The surface on which the ball is to be drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx The change in x-coordinate per unit time
     * @param dy The change in y-coordinate per unit time
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Moves the ball one step based on its velocity.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Handles the collision of the ball with the inside of a given frame.
     * If the ball hits the frame, it changes its velocity accordingly to simulate a bounce.
     * The function checks the ball's current position and velocity, and if a collision is detected,
     * it flips the velocity in the appropriate direction.
     *
     * @param frame The frame within which the ball is moving
     */
    public void collideWithFrameInside(Rectangle frame) {
        // Get the current velocity components of the ball
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();

        // Check if the ball is hitting the left or right side of the frame
        // If it is, flip the x-component of the velocity
        if ((this.getX() + (this.getSize() + dx) >= frame.getLowerRight().getX())
                || (this.getX() - (this.getSize() - dx) <= frame.getUpperLeft().getX())) {
            dx = -dx;
        }

        // Check if the ball is hitting the top or bottom side of the frame
        // If it is, flip the y-component of the velocity
        if ((this.getY() + (this.getSize() + dy) >= frame.getLowerRight().getY())
                || (this.getY() - (this.getSize() - dy) <= frame.getUpperLeft().getY())) {
            dy = -dy;
        }

        // Set the new velocity of the ball
        this.setVelocity(dx, dy);
    }

    /**
     * Collides the ball with the frame of the screen.
     * If the ball hits the frame, it changes its flips its velocity accordingly.
     * the function will check where the ball is coming from
     * and will decide exactly how to rebound it off the frame.
     *
     * @param frame The frame of the screen
     */
    public void collideWithFrameOutside(Rectangle frame) {
        Line leftLine = frame.getLeftLine();
        Line topLine = frame.getTopLine();
        Line rightLine = frame.getRightLine();
        Line bottomLine = frame.getBottomLine();

        // Treat balls with radius less than 10 as size 10 balls (in order to avoid jittering).
        double size = Math.max(this.getSize(), 10);

        Point ballEdgeBottomRight = new Point(this.getX() + size, this.getY() + size);
        Point ballEdgeUpperRight = new Point(this.getX() + size, this.getY() - size);
        Point ballEdgeBottomLeft = new Point(this.getX() - size, this.getY() + size);
        Point ballEdgeUpperLeft = new Point(this.getX() - size, this.getY() - size);

        Line ballEdgeBottomRightVector = new Line(this.center, ballEdgeBottomRight);
        Line ballEdgeUpperRightVector = new Line(this.center, ballEdgeUpperRight);
        Line ballEdgeBottomLeftVector = new Line(this.center, ballEdgeBottomLeft);
        Line ballEdgeUpperLeftVector = new Line(this.center, ballEdgeUpperLeft);

        boolean isNearCenter = false;
        // Collision with the left side of the frame
        if (leftLine.isIntersecting(ballEdgeBottomRightVector) || leftLine.isIntersecting(ballEdgeUpperRightVector)) {
            isNearCenter = this.center.getX()
                    - (frame.getUpperLeft().getX() + frame.getLowerRight().getX()) / 2 < Threshold.TOLERANCE;
            this.center.setX(
                    isNearCenter ? frame.getUpperLeft().getX() - size
                            : frame.getLowerRight().getX() + size);
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }

        // Collision with the upper side of the frame
        if (topLine.isIntersecting(ballEdgeBottomLeftVector) || topLine.isIntersecting(ballEdgeBottomRightVector)) {
            isNearCenter = this.center.getY()
                    - (frame.getUpperLeft().getY() + frame.getLowerRight().getY()) / 2 < Threshold.TOLERANCE;
            this.center.setY(
                    isNearCenter ? frame.getUpperLeft().getY() - size
                            : frame.getLowerRight().getY() + size);
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }

        // Collision with the right side of the frame
        if (rightLine.isIntersecting(ballEdgeUpperLeftVector) || rightLine.isIntersecting(ballEdgeBottomLeftVector)) {
            isNearCenter = this.center.getX()
                    - (frame.getUpperLeft().getX() + frame.getLowerRight().getX()) / 2 < Threshold.TOLERANCE;
            this.center.setX(
                    isNearCenter ? frame.getUpperLeft().getX() - size
                            : frame.getLowerRight().getX() + size);
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }

        // Collision with the lower side of the frame
        if (bottomLine.isIntersecting(ballEdgeUpperLeftVector) || bottomLine.isIntersecting(ballEdgeUpperRightVector)) {
            isNearCenter = this.center.getY()
                    - (frame.getUpperLeft().getY() + frame.getLowerRight().getY()) / 2 < Threshold.TOLERANCE;
            this.center.setY(
                    isNearCenter ? frame.getUpperLeft().getY() - size
                            : frame.getLowerRight().getY() + size);
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
    }
}
