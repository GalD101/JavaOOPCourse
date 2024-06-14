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
     * Notifies the object that a collision occurred at a specific point with a given velocity.
     * The method returns the new velocity expected after the hit. This is based on the force
     * the object inflicted on us during the collision.
     *
     * @param collisionPoint  The point at which the collision occurred
     * @param currentVelocity The velocity of the object at the time of the collision
     * @return The new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
