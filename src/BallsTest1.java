// 322558297 Gal Dali

import biuoop.GUI;
import biuoop.DrawSurface;
import objects.Ball;
import objects.Point;

/**
 * This class represents a simple animation of a ball bouncing within a frame.
 */
public class BallsTest1 {

    /**
     * Initiates an animation of a ball bouncing within a frame.
     * The method creates a GUI window, a sleeper,
     * and a ball at the given start point with the given velocity.
     * The ball will continue to bounce within the frame indefinitely.
     *
     * @param start The starting point of the ball.
     * @param dx    The initial x component of the velocity.
     * @param dy    The initial y component of the velocity.
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            // Negate the x velocity component when hitting the right || left wall
            if ((ball.getX() + (ball.getSize() + dx) > 200) || (ball.getX() - (ball.getSize() - dx) < 0)) {
                dx = -dx;
            }
            // Negate the y velocity component when hitting the top || bottom wall
            if ((ball.getY() + (ball.getSize() + dy) > 200) || (ball.getY() - (ball.getSize() - dy) < 0)) {
                dy = -dy;
            }
            ball.setVelocity(dx, dy);
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * The main method of the class.
     * It calls the drawAnimation method with a starting point and initial velocity.
     *
     * @param args The command line arguments, which are not used in this case.
     */
    public static void main(String[] args) {
        drawAnimation(new Point(100, 100), 0, 30);
    }
}