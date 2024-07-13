// 322558297 Gal Dali

package objects;

import animations.Velocity;

/**
 * The Collidable interface represents objects that can participate in collisions.
 * Each Collidable object has a "collision shape" represented as a Rectangle object.
 * When a collision occurs, the Collidable object is notified, and it returns the new velocity expected after the hit.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     * The collision shape is represented as a Rectangle object.
     *
     * @return The Rectangle object representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it.
     * This method is called when a collision is detected with the object.
     * The method receives the collision point and the current velocity of the object at the time of the collision.
     * It calculates and returns the new velocity of the object after the collision.
     *
     * @param hitter          The ball that hit the object
     * @param collisionPoint  The point at which the collision occurred
     * @param currentVelocity The current velocity of the object at the time of the collision
     * @return The new velocity of the object after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
