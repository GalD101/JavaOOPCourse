package game;

import objects.Collidable;
import objects.Point;

public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    // constructor
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint.getX(), collisionPoint.getY());
        this.collisionObject = collisionObject; // TODO - encapsulate this
    }

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(), this.collisionPoint.getY());
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject; // TODO - encapsulate this
    }
}
