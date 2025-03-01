// 322558297 Gal Dali

package objects;

import animations.Velocity;
import biuoop.DrawSurface;
import events.HitListener;
import events.HitNotifier;
import game.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block in a game. It implements the Collidable interface,
 * meaning it can participate in collisions with other game objects.
 * Each block has a rectangular shape and a color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private boolean isSideBlock = false;

    /**
     * Constructs a new Block object.
     *
     * <p>This constructor creates a new Block object with a specified collision rectangle and color.
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
        this.hitListeners = new ArrayList<>();
        this.isSideBlock = false;
    }

    /**
     * Constructs a new Block object with a specified collision rectangle, color, and side block flag.
     * This constructor delegates to the primary constructor
     * to initialize the block with a collision rectangle and color,
     * and then sets the isSideBlock flag to indicate whether the block is positioned at the side of the game area.
     * Side blocks may have different behaviors or properties in the game.
     *
     * @param collisionRectangle The rectangle that represents the size and position of the block. Must not be null.
     * @param color              The color of the block. If null, defaults to black in the primary constructor.
     * @param isSideBlock        A boolean flag indicating if the block is a side block, affecting its behavior.
     */
    public Block(Rectangle collisionRectangle, Color color, boolean isSideBlock) {
        this(collisionRectangle, color);
        this.isSideBlock = isSideBlock;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        // check if hl is in the list before attempting to remove it
        if (this.hitListeners.contains(hl)) {
            this.hitListeners.remove(hl);
        }
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns a copy of the block's collision rectangle.
     *
     * <p>This method creates a new Rectangle object
     * that has the same upper left and lower right points as the block's collision rectangle.
     * The new rectangle is a copy, so changes to it will not affect the block's collision rectangle.
     *
     * @return A new Rectangle object that represents the block's collision shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getLowerRight());
    }

    /**
     * Adds this block to the specified game.
     *
     * <p>This method adds the block to the game as both a collidable and a sprite.
     * Collidables are objects that can participate in collisions.
     * Sprites are objects that can be drawn on the screen.
     *
     * @param game The game to which the block is to be added
     */
    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Returns the color of the block.
     *
     * <p>This method returns the color of the block.
     * The color is directly assigned during the construction of the block.
     * The returned color is the same as the color used to draw the block.
     *
     * @return The color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws this block on the given DrawSurface.
     *
     * <p>This method sets the color of the DrawSurface to the color of this block,
     * then fills a rectangle on the DrawSurface with the dimensions and position of this block.
     * After that, it sets the color of the DrawSurface to black and draws the outline of the rectangle.
     *
     * @param d The DrawSurface on which this block is to be drawn
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
        d.setColor(Color.black);

        d.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     * This method is called every time a time unit passes in the game.
     * Currently, it does not perform any action.
     */
    @Override
    public void timePassed() {
        // currently do nothing
    }

    /**
     * Calculates and returns the new velocity after a collision.
     *
     * <p>This method calculates the new velocity of an object after it hits the block.
     * The method first checks if the collision point or the current velocity is null.
     * If either is null, it returns the current velocity.
     * Then, it checks if the collision point is on the top, bottom, left, or right side of the block.
     * If the collision point is on the left or right side, it inverts the x direction of the velocity.
     * If the collision point is on the top or bottom side, it inverts the y direction of the velocity.
     * If the collision point is on an edge (corner), it inverts both the x and y directions of the velocity.
     * The method then returns the new velocity.
     *
     * @param collisionPoint  The point where the collision occurs. Can be null.
     * @param currentVelocity The current velocity of the object that is hitting the block. Can be null.
     * @return The new velocity after the collision,
     * or the current velocity if the collision point or the current velocity is null.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!this.ballColorMatch(hitter)) {
            if (!this.isSideBlock) {
                hitter.setColor(this.getColor());
            }
            this.notifyHit(hitter);
        }
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

    /**
     * Checks if the color of the ball matches the color of this block.
     * This method compares the color of the specified ball with the color of this block.
     * It returns true if the colors match, indicating that the ball and block have the same color.
     * Otherwise, it returns false.
     *
     * @param ball The ball whose color is to be compared with this block's color.
     * @return true if the ball's color matches this block's color, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.color);
    }

    /**
     * Removes this block from the specified game.
     * This method ensures that the block is no longer part of the game by removing it from both
     * the list of collidables and the list of sprites within the game. This is essential for when
     * the block is destroyed or needs to be removed from the game for any reason, ensuring that it
     * no longer participates in collision detection or rendering.
     *
     * @param game The game instance from which the block is to be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
