package animations.tests;

import objects.Point;
import animations.Velocity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VelocityTest {

    @Test
    public void applyToPoint_changesPointPosition() {
        Velocity velocity = new Velocity(1.0, 2.0);
        Point initialPoint = new Point(0.0, 0.0);
        Point expectedPoint = new Point(1.0, 2.0);

        Point resultPoint = velocity.applyToPoint(initialPoint);

        assertEquals(expectedPoint.getX(), resultPoint.getX());
        assertEquals(expectedPoint.getY(), resultPoint.getY());
    }

    @Test
    public void getDx_returnsCorrectDx() {
        Velocity velocity = new Velocity(1.0, 2.0);

        double resultDx = velocity.getDx();

        assertEquals(1.0, resultDx);
    }

    @Test
    public void getDy_returnsCorrectDy() {
        Velocity velocity = new Velocity(1.0, 2.0);

        double resultDy = velocity.getDy();

        assertEquals(2.0, resultDy);
    }

    @Test
    public void fromAngleAndSpeed_createsCorrectVelocity() {
        double angle = 45.0;
        double speed = 1.0;
        double expectedDx = Math.cos(Math.toRadians(angle)) * speed;
        double expectedDy = Math.sin(Math.toRadians(angle)) * speed;

        Velocity resultVelocity = Velocity.fromAngleAndSpeed(angle, speed);

        assertEquals(expectedDx, resultVelocity.getDx());
        assertEquals(expectedDy, resultVelocity.getDy());
    }
}