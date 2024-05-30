package objects;

import animations.Velocity;
import biuoop.DrawSurface;

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
        surface.fillCircle(this.getX(), this.getY(), this.r);
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

    public void collideWithFrameInside(Rectangle frame) {
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();
        if ((this.getX() + (this.getSize() + dx) >= frame.getLowerRight().getX()) || (this.getX() - (this.getSize() - dx) <= frame.getUpperLeft().getX())) {
            dx = -dx;
        }
        if ((this.getY() + (this.getSize() + dy) >= frame.getLowerRight().getY()) || (this.getY() - (this.getSize() - dy) <= frame.getUpperLeft().getY())) {
            dy = -dy;
        }
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
        // get the horizontal velocity component
        double dx = this.getVelocity().getDx();

        // get the vertical velocity component
        double dy = this.getVelocity().getDy();

        // The previous position is defined as the position of the center
        // of the ball *before* the moveOneStep method is called.
        double prevPosXMovingRight = this.getX() + this.getSize();
        double prevPosYMovingDown = this.getY() + this.getSize();

        double prevPosXMovingLeft = this.getX() - this.getSize();
        double prevPosYMovingUp = this.getY() - this.getSize();

        // The next position is defined as the position of the center
        // of the ball *after* the moveOneStep method is called.
        double nextPosXMovingRight = prevPosXMovingRight + dx;
        double nextPosYMovingDown = prevPosYMovingDown + dy;

        double nextPosXMovingLeft = prevPosXMovingLeft + dx;
        double nextPosYMovingUp = prevPosYMovingUp + dy;

        // add if clause for cases the ball hits the edge of the rectangle
        if (frame.getUpperLeft().getX() == prevPosXMovingRight || frame.getLowerRight().getX() == prevPosXMovingLeft) {
            dx = -dx;
        }
        if (frame.getUpperLeft().getY() == prevPosYMovingDown || frame.getLowerRight().getY() == prevPosYMovingUp) {
            dy = -dy;
        }

        // moving right
        if (dx >= 0) {
            if (prevPosXMovingRight <= frame.getUpperLeft().getX() && nextPosXMovingRight >= frame.getUpperLeft().getX()) {
                if (dy > 0) {
                    if (frame.getUpperLeft().getY() <= prevPosYMovingUp && prevPosYMovingUp <= frame.getLowerRight().getY()) {
                        dx = -dx;
                    }
                } else {
                    if (frame.getUpperLeft().getY() <= prevPosYMovingDown && prevPosYMovingDown <= frame.getLowerRight().getY()) {
                        dx = -dx;
                    }
                }
            }
        } else { // moving left
            if (prevPosXMovingLeft >= frame.getLowerRight().getX() && nextPosXMovingLeft <= frame.getLowerRight().getX()) {
                if (dy > 0) {
                    if (frame.getUpperLeft().getY() <= prevPosYMovingUp && prevPosYMovingUp <= frame.getLowerRight().getY()) {
                        dx = -dx;
                    }
                } else {
                    if (frame.getUpperLeft().getY() <= prevPosYMovingDown && prevPosYMovingDown <= frame.getLowerRight().getY()) {
                        dx = -dx;
                    }
                }
            }
        }

        // moving down
        if (dy >= 0) {
            if (prevPosYMovingDown <= frame.getUpperLeft().getY() && nextPosYMovingDown >= frame.getUpperLeft().getY()) {
                if (dx > 0) {
                    if (frame.getUpperLeft().getX() <= prevPosXMovingLeft && prevPosXMovingLeft <= frame.getLowerRight().getX()) {
                        dy = -dy;
                    }
                } else {
                    if (frame.getUpperLeft().getX() <= prevPosXMovingRight && prevPosXMovingRight <= frame.getLowerRight().getX()) {
                        dy = -dy;
                    }
                }
            }
        } else { // moving up
            if (prevPosYMovingUp >= frame.getLowerRight().getY() && nextPosYMovingUp <= frame.getLowerRight().getY()) {
                if (dx > 0) {
                    if (frame.getUpperLeft().getX() <= prevPosXMovingLeft && prevPosXMovingLeft <= frame.getLowerRight().getX()) {
                        dy = -dy;
                    }
                } else {
                    if (frame.getUpperLeft().getX() <= prevPosXMovingLeft && prevPosXMovingLeft <= frame.getLowerRight().getX()) {
                        dy = -dy;
                    }
                }
            }
        }
        // The following code needs to first detect where the ball is coming from
        // i.e. from the left side of the wall, right side, top or bottom.
        // Then, change the velocity of the ball accordingly (flip the corresponding velocity component).


//        if (prevPosX2 <= frame.getUpperLeft().getX() && nextPosX2 >= frame.getUpperLeft().getX()) {
//            if (!(prevPosY2 <= frame.getUpperLeft().getY() || prevPosY2 >= frame.getLowerRight().getY())) {
//                dx = -dx;
//            }
//        }
//        else if (prevPosX1 >= frame.getLowerRight().getX() && nextPosX1 <= frame.getLowerRight().getX()) {
//            if (!(prevPosY1 <= frame.getUpperLeft().getY() || prevPosY1 >= frame.getLowerRight().getY())) {
//                dx = -dx;
//            }
//        }
//        if (prevPosY1 >= frame.getLowerRight().getY() && nextPosY1 <= frame.getLowerRight().getY()) {
//            if (!(prevPosX1 <= frame.getUpperLeft().getX() || prevPosX1 >= frame.getLowerRight().getX())) {
//                dy = -dy;
//            }
//        }
//        else if (prevPosY2 <= frame.getUpperLeft().getY() && nextPosY2 >= frame.getUpperLeft().getY()) {
//            if (!(prevPosX1 <= frame.getUpperLeft().getX() || prevPosX1 >= frame.getLowerRight().getX())) {
//                dy = -dy;
//            }
//        }
        this.setVelocity(dx, dy);
    }
}