package objects;

import animations.Velocity;

import java.awt.Color;

/**
 * The Block class represents a block in a game. It implements the Collidable interface,
 * meaning it can participate in collisions with other game objects.
 * Each block has a rectangular shape and a color.
 */
public class Block implements Collidable {
    private Rectangle collisionRectangle;
    private Color color;

    /**
     * Constructs a new Block object.
     * <p>
     * This constructor creates a new Block object with a specified collision rectangle and color.
     * The collision rectangle is copied to prevent external modifications to the rectangle from affecting the block.
     * The color is directly assigned.
     *
     * @param collisionRectangle The rectangle that represents the size and position of the block. Must not be null.
     * @param color              The color of the block.
     * @throws IllegalArgumentException if collisionRectangle is null.
     */
    public Block(Rectangle collisionRectangle, Color color) {
        if (collisionRectangle == null) {
            throw new IllegalArgumentException("The rectangle must not be null.");
        }
        this.collisionRectangle = new Rectangle(collisionRectangle.getUpperLeft(),
                collisionRectangle.getLowerRight());
        this.color = color == null ? Color.BLACK : color;
    }

    /**
     * Returns a copy of the block's collision rectangle.
     * <p>
     * This method creates a new Rectangle object that has the same upper left and lower right points as the block's collision rectangle.
     * The new rectangle is a copy, so changes to it will not affect the block's collision rectangle.
     *
     * @return A new Rectangle object that represents the block's collision shape.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getLowerRight());
    }

    /**
     * Returns the color of the block.
     * <p>
     * This method returns the color of the block. The color is directly assigned during the construction of the block.
     * The returned color is the same as the color used to draw the block.
     *
     * @return The color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    // TODO: Change the docs in case you change the logic (if there is jitter or what not)

    /**
     * Calculates and returns the new velocity after a collision.
     * <p>
     * This method calculates the new velocity of an object after it hits the block.
     * The method first checks if the collision point or the current velocity is null. If either is null, it returns the current velocity.
     * Then, it checks if the collision point is on the top, bottom, left, or right side of the block.
     * If the collision point is on the left or right side, it inverts the x direction of the velocity.
     * If the collision point is on the top or bottom side, it inverts the y direction of the velocity.
     * If the collision point is on an edge (corner), it inverts both the x and y directions of the velocity.
     * The method then returns the new velocity.
     *
     * @param collisionPoint  The point where the collision occurs. Can be null.
     * @param currentVelocity The current velocity of the object that is hitting the block. Can be null.
     * @return The new velocity after the collision, or the current velocity if the collision point or the current velocity is null.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return currentVelocity;
        }

        boolean isOnTop = this.getCollisionRectangle().getTopLine().isPointOnLine(collisionPoint);
        boolean isOnBottom = this.getCollisionRectangle().getBottomLine().isPointOnLine(collisionPoint);
        boolean isOnLeft = this.getCollisionRectangle().getLeftLine().isPointOnLine(collisionPoint);
        boolean isOnRight = this.getCollisionRectangle().getRightLine().isPointOnLine(collisionPoint);

        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        if (isOnLeft || isOnRight) {
            newDx = -1 * newDx;
        }
        if (isOnTop || isOnBottom) {
            newDy = -1 * newDy;
        }
        return new Velocity(newDx, newDy);
    }
}
