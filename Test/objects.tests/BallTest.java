package objects.tests;

import game.CollisionInfo;
import game.GameEnvironment;
import objects.Rectangle;
import org.junit.jupiter.api.Test;
import objects.Ball;
import objects.Point;
import animations.Velocity;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    @Test
    void ballMovesCorrectlyWithPositiveVelocity() {
        Ball ball = new Ball(new Point(0, 0), 10, Color.BLACK);
        ball.setVelocity(5, 5);
        ball.moveOneStep();
        assertEquals(5, ball.getX());
        assertEquals(5, ball.getY());
    }

    @Test
    void ballMovesCorrectlyWithNegativeVelocity() {
        Ball ball = new Ball(new Point(10, 10), 10, Color.BLACK);
        ball.setVelocity(-5, -5);
        ball.moveOneStep();
        assertEquals(5, ball.getX());
        assertEquals(5, ball.getY());
    }

    @Test
    void ballDoesNotMoveWithZeroVelocity() {
        Ball ball = new Ball(new Point(10, 10), 10, Color.BLACK);
        ball.setVelocity(0, 0);
        ball.moveOneStep();
        assertEquals(10, ball.getX());
        assertEquals(10, ball.getY());
    }

    @Test
    void ballColorIsCorrect() {
        Ball ball = new Ball(new Point(10, 10), 10, Color.RED);
        assertEquals(Color.RED, ball.getColor());
    }

    @Test
    void ballSizeIsCorrect() {
        Ball ball = new Ball(new Point(10, 10), 10, Color.RED);
        assertEquals(10, ball.getSize());
    }

    @Test
    void ballVelocityIsCorrect() {
        Ball ball = new Ball(new Point(10, 10), 10, Color.RED);
        ball.setVelocity(5, 5);
        Velocity v = ball.getVelocity();
        assertEquals(5, v.getDx());
        assertEquals(5, v.getDy());
    }

    @Test
    void ballMovesOneStepWithoutCollision() {
        GameEnvironment gameEnvironment = new GameEnvironment();
        Ball ball = new Ball(new Point(0, 0), 10, Color.BLACK, gameEnvironment);
        ball.setVelocity(5, 5);
        ball.moveOneStep();

        assertEquals(5, ball.getX());
        assertEquals(5, ball.getY());
    }

    @Test
    void ballMovesOneStepWithoutGameEnvironment() {
        Ball ball = new Ball(new Point(0, 0), 10, Color.BLACK);
        ball.setVelocity(5, 5);
        ball.moveOneStep();

        assertEquals(5, ball.getX());
        assertEquals(5, ball.getY());
    }
}