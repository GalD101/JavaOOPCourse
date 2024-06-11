package game;

import objects.Collidable;
import objects.Point;

/**
 * The CollisionInfo class represents the information about a collision in the game.
 * It contains the point at which the collision occurs and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a new CollisionInfo object.
     *
     * @param collisionPoint  The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint.getX(), collisionPoint.getY());
        this.collisionObject = collisionObject; // TODO - encapsulate this
    }

    /**
     * Returns a new Point object representing the point at which the collision occurs.
     * This method creates a new Point object to avoid exposing the internal state of the CollisionInfo object.
     *
     * @return A new Point object representing the collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(), this.collisionPoint.getY());
    }

    /**
     * Returns the collidable object involved in the collision.
     * This method directly exposes the internal state of the CollisionInfo object.
     * TODO - Consider returning a copy or a read-only view to preserve encapsulation.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject; // TODO - encapsulate this
    }
}
