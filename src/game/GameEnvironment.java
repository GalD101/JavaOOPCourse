package game;

import objects.Collidable;
import objects.Line;
import objects.Point;

import java.util.ArrayList;

/**
 * The GameEnvironment class represents the environment of the game.
 * It maintains a list of Collidable objects that can interact with each other in the game.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidableList;

    /**
     * Default constructor for the GameEnvironment class.
     * Initializes the list of Collidable objects that the game environment will keep track of.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * Adds a given Collidable object to the game environment.
     *
     * @param c The Collidable object to be added to the game environment.
     */
    public void addCollidable(Collidable c) {
        if (c == null) {
            return;
        }
        this.collidableList.add(c);
    }

    /**
     * return the collidable list.
     *
     * @return the collidable list.
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidableList;
    }

    /**
     * This method calculates the closest collision that an object moving along a given trajectory will have.
     * The object is assumed to move from the start of the line to the end of the line.
     * If the object does not collide with any of the collidables in this collection, the method returns null.
     * Otherwise, it returns information about the closest collision that is going to occur.
     *
     * @param trajectory The trajectory along which the object is moving.
     * @return CollisionInfo object containing information about the closest collision. If no collision is going to occur, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (trajectory == null) {
            return null;
        }

        java.util.List<Point> collisionPoints = new java.util.ArrayList<Point>();
        java.util.List<Collidable> collidablesInTrajectory = new java.util.ArrayList<Collidable>();

        // Filter only relevant collidables
        for (Collidable collidable : this.collidableList) {
            Point currentCollisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (currentCollisionPoint != null) {
                // Add the current collision point to the list
                collisionPoints.add(currentCollisionPoint);
                // Add the current collidable to the list of collidables in the trajectory
                collidablesInTrajectory.add(collidable);
            }
        }

        Point closestCollisionPoint = collisionPoints.isEmpty() ? null : collisionPoints.get(0);
        Collidable closestCollisionCollidable = collidablesInTrajectory.isEmpty() ? null : collidablesInTrajectory.get(0);

        // Find the closest collision point to trajectory.start()
        for (int i = 0; i < collisionPoints.size(); i++) {
            if (collisionPoints.get(i).distance(trajectory.start()) < closestCollisionPoint.distance(trajectory.start())) {
                closestCollisionPoint = collisionPoints.get(i);
                closestCollisionCollidable = collidablesInTrajectory.get(i);
            }
        }
        if (closestCollisionPoint == null || closestCollisionCollidable == null) {
            return null;
        }
        return new CollisionInfo(closestCollisionPoint, closestCollisionCollidable);
    }
}
