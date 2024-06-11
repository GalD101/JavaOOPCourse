package game;

import objects.*;
import objects.Point;
import objects.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

class GameEnvironmentTest {

    @Test
    void getClosestCollision_returnsNull_whenTrajectoryIsNull() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        assertNull(gameEnvironment.getClosestCollision(null));
    }

    @Test
    void getClosestCollision_returnsNull_whenNoCollidables() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        Line trajectory = new Line(new Point(0, 0), new Point(10, 10));
        assertNull(gameEnvironment.getClosestCollision(trajectory));
    }

    @Test
    void getClosestCollision_returnsClosestCollision_whenCollidablesExist() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        Line trajectory = new Line(new Point(0, 0), new Point(10, 10));
        Collidable collidable1 = new Block(new Rectangle(new Point(1, 1), 2, 2), Color.BLACK);
        Collidable collidable2 = new Block(new Rectangle(new Point(5, 5), 2, 2), Color.BLUE);
        gameEnvironment.addCollidable(collidable1);
        gameEnvironment.addCollidable(collidable2);
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        assertNotNull(collisionInfo);
        assertEquals(collidable1, collisionInfo.collisionObject());
    }

    @Test
    void testGetClosestCollision() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        Block b1 = new Block(new Rectangle(new Point(0, 0), new Point(5, 5)), Color.BLACK);
        Block b2 = new Block(new Rectangle(new Point(10, 0), new Point(15, 5)), Color.BLACK);
        Line trajectory = new Line(new Point(0, 2.5), new Point(20, 2.5));
        assertNull(gameEnvironment.getClosestCollision(trajectory));
        gameEnvironment.addCollidable(b2);
        assertEquals(gameEnvironment.getClosestCollision(trajectory).collisionObject(), b2);
        assertTrue(new Point(10, 2.5).equals(gameEnvironment.getClosestCollision(trajectory).collisionPoint()));

        gameEnvironment.addCollidable(b1);
        assertEquals(gameEnvironment.getClosestCollision(trajectory).collisionObject(), b1);
        assertTrue(new Point(0, 2.5).equals(gameEnvironment.getClosestCollision(trajectory).collisionPoint()));
    }
}