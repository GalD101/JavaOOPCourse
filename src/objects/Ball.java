// 322558297 Gal Dali

package objects;

import animations.Velocity;
import biuoop.DrawSurface;
import game.Game;
import game.GameEnvironment;
import game.CollisionInfo;

import static game.GameSettings.BALL_BORDER_COLOR;
import static game.GameSettings.BALL_FILL_COLOR;
import static game.GameSettings.BALL_CENTER_POINT_COLOR;
import static game.GameSettings.BALL_MAX_SIZE;

/**
 * Ball class represents a ball object in a 2D space.
 * It has properties like center, radius, color and velocity.
 */
public class Ball implements Sprite {
    private Point center; // The center point of the ball
    private int r; // The radius of the ball
    private java.awt.Color color; // The color of the ball
    private Velocity velocity; // The velocity of the ball
    private GameEnvironment gameEnvironment; // The game environment in which the ball moves

    /**
     * Constructor that initializes a new Ball object with a center point, radius and color.
     *
     * @param center          The center point of the ball
     * @param r               The radius of the ball
     * @param color           The color of the ball
     * @param gameEnvironment The game environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = (center == null) ? new Point(0, 0) : new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructor that initializes a new Ball object with a center point, radius and color.
     *
     * @param center The center point of the ball
     * @param r      The radius of the ball
     * @param color  The color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this(center, r, color, null);
    }

    /**
     * Constructor that initializes a new Ball object with a center point (x, y coordinates), radius and color.
     *
     * @param centerX         The x-coordinate of the center point of the ball
     * @param centerY         The y-coordinate of the center point of the ball
     * @param r               The radius of the ball
     * @param color           The color of the ball
     * @param gameEnvironment The game environment of the ball
     */
    public Ball(double centerX, double centerY, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(new Point(centerX, centerY), r, color, gameEnvironment);
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
        this(new Point(centerX, centerY), r, color, null);
    }

    /**
     * Adds this ball to the specified game.
     *
     * @param game The game to which the ball is to be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
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
     * Returns a new Point object that represents the center of the ball.
     * The new Point object is a copy of the current center point of the ball.
     *
     * @return A new Point object representing the center of the ball
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
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
        surface.setColor(BALL_FILL_COLOR);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(BALL_BORDER_COLOR);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(BALL_CENTER_POINT_COLOR);
        surface.fillCircle(this.getX(), this.getY(), 2);
    }

    /**
     * This method is called every time a time unit passes in the game.
     * It triggers the ball to move one step based on its current velocity.
     */
    public void timePassed() {
        this.moveOneStep();
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
     * Sets the center point of the ball.
     *
     * @param center The new center point of the ball
     */
    public void setCenter(Point center) {
        this.center = new Point(center.getX(), center.getY());
    }

    /**
     * Moves the ball one step based on its velocity
     * and if it will hit an object in the trajectory.
     */
    public void moveOneStep() {
        // Assume the ball is small enough
        if (this.gameEnvironment == null) {
            this.setCenter(this.getVelocity().applyToPoint(this.center));
            return;
        }
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            // In case of no collision:
            this.setCenter(this.getVelocity().applyToPoint(this.center));
            return;
        }

        // There is a hit!
        // Fix the position of the ball in case it hits the moving paddle on the sides
        if (collisionInfo.collisionObject().getClass().getName().equals("objects.Paddle")) {
            final int reposition = 3 * BALL_MAX_SIZE;
            Rectangle rect = collisionInfo.collisionObject().getCollisionRectangle();
            Point collisionPoint = collisionInfo.collisionPoint();
            if (rect.getLeftLine().isPointOnLine(collisionPoint)) {
                this.setCenter(new Point(rect.getUpperLeft().getX() - reposition,
                        collisionPoint.getY()));
            } else if (rect.getRightLine().isPointOnLine(collisionPoint)) {
                this.setCenter(new Point(rect.getLowerRight().getX() + reposition,
                        collisionPoint.getY()));
            }
        } else {
            // Normal collision
            // Move the ball to "almost" the hit point
            this.setCenter(new Point(collisionInfo.collisionPoint().getX() - this.getVelocity().getDx() / 2,
                    collisionInfo.collisionPoint().getY() - this.getVelocity().getDy() / 2));
        }

        Velocity newVelocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), this.getVelocity());
        this.setVelocity(newVelocity);
    }
}
