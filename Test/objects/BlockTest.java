package objects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Threshold;
import animations.Velocity;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    @Test
    void constructorShouldThrowExceptionWhenRectangleIsNull() {
        Color color = Color.RED;
        assertThrows(IllegalArgumentException.class, () -> new Block(null, color));
    }

    @Test
    void hitShouldReturnCurrentVelocityWhenCollisionPointIsNull() {
        Block block = new Block(new Rectangle(new Point(0, 0), new Point(10, 10)), Color.RED);
        Velocity currentVelocity = new Velocity(5, 5);

        Velocity newVelocity = block.hit(null, null, currentVelocity);

        assertEquals(currentVelocity, newVelocity);
    }

    @Test
    void hitShouldReturnCurrentVelocityWhenCurrentVelocityIsNull() {
        Block block = new Block(new Rectangle(new Point(0, 0), new Point(10, 10)), Color.RED);
        Point collisionPoint = new Point(5, 5);

        Velocity newVelocity = block.hit(null, collisionPoint, null);

        assertNull(newVelocity);
    }

    @Test
    void hitShouldInvertXDirectionWhenCollisionPointIsOnLeftOrRight() {
        Block block = new Block(new Rectangle(new Point(0, 0), new Point(10, 10)), Color.RED);
        Point collisionPoint = new Point(0, 5);  // On the left side
        Velocity currentVelocity = new Velocity(5, 5);

        Velocity newVelocity = block.hit(null, collisionPoint, currentVelocity);

        assertEquals(-5, newVelocity.getDx());
        assertEquals(5, newVelocity.getDy());
    }

    @Test
    void hitShouldInvertYDirectionWhenCollisionPointIsOnTopOrBottom() {
        Block block = new Block(new Rectangle(new Point(0, 0), new Point(10, 10)), Color.RED);
        Point collisionPoint = new Point(5, 0);  // On the top side
        Velocity currentVelocity = new Velocity(5, 5);

        Velocity newVelocity = block.hit(null, collisionPoint, currentVelocity);

        assertEquals(5, newVelocity.getDx());
        assertEquals(-5, newVelocity.getDy());
    }

    @Test
    void hitShouldInvertBothDirectionsWhenCollisionPointIsOnCorner() {
        Block block = new Block(new Rectangle(new Point(0, 0), new Point(10, 10)), Color.RED);
        Velocity currentVelocity = new Velocity(5, 5);

        // Check upper left corner
        Point collisionPoint = new Point(0, 0);
        Velocity newVelocity = block.hit(null, collisionPoint, currentVelocity);
        assertEquals(-5, newVelocity.getDx());
        assertEquals(-5, newVelocity.getDy());

        // Check lower right corner
        collisionPoint = new Point(10, 10);
        newVelocity = block.hit(null, collisionPoint, currentVelocity);
        assertEquals(-5, newVelocity.getDx());
        assertEquals(-5, newVelocity.getDy());

        // Check upper right corner
        collisionPoint = new Point(10, 0);
        newVelocity = block.hit(null, collisionPoint, currentVelocity);
        assertEquals(-5, newVelocity.getDx());
        assertEquals(-5, newVelocity.getDy());

        // Check lower left corner
        collisionPoint = new Point(0, 10);
        newVelocity = block.hit(null, collisionPoint, currentVelocity);
        assertEquals(-5, newVelocity.getDx());
        assertEquals(-5, newVelocity.getDy());
    }
}
