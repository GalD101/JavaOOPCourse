// 322558297 Gal Dali

package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import objects.Ball;
import objects.Point;

import static utils.InputValidator.stringToInteger;
import static utils.InputValidator.stringToPositiveInteger;

/**
 * This class is responsible for creating a bouncing ball animation.
 * The ball bounces off the edges of the screen.
 */
public class BouncingBallAnimation {

    /**
     * Draws the animation of the bouncing ball.
     * The ball's velocity is updated based on its position and the screen's edges.
     *
     * @param start The starting point of the ball.
     * @param dx    The change in x-coordinate per step.
     * @param dy    The change in y-coordinate per step.
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        final int width = 200;
        final int height = 200;
        final int edges = 0;
        final int radius = 30;
        GUI gui = new GUI("Bouncing ball animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        if (start.getX() > width || start.getY() > height) {
            System.out.println("Invalid input. Please enter 4 integers. The first 2 must be non negative"
                    + " and less than " + width + " and " + height + " respectively."
                    + " We will adjust the ball to be within the screen.");
            start.setX(width / 2);
            start.setY(height / 2);
        }

        // If the center is positioned such that the ball is a little bit out of the screen, adjust it
        if (start.getX() < radius) {
            start.setX(radius + start.getX());
        }
        if (start.getY() < radius) {
            start.setY(radius + start.getY());
        }
        if (start.getX() > width - radius) {
            start.setX(start.getX() - radius);
        }
        if (start.getY() > height - radius) {
            start.setY(start.getY() - radius);
        }
        Ball ball = new Ball(start.getX(), start.getY(), radius, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            // Negate the x velocity component when hitting the right || left wall
            if ((ball.getX() + (ball.getSize() + dx) > width) || (ball.getX() - (ball.getSize() - dx) < edges)) {
                dx = -dx;
            }
            // Negate the y velocity component when hitting the top || bottom wall
            if ((ball.getY() + (ball.getSize() + dy) > height) || (ball.getY() - (ball.getSize() - dy) < edges)) {
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
     * The main method that creates an instance of BouncingBallAnimation and calls the drawAnimation method.
     * It takes four command line arguments:
     * the x and y coordinates of the center of the ball, and the dx and dy values.
     *
     * @param args Command line arguments which will be: centerX centerY dx dy.
     */
    public static void main(String[] args) {
        // Check if the input is valid
        if (args.length != 4) {
            System.out.println("Usage: BouncingBallAnimation <centerX> <centerY> <dx> <dy>");
            args = new String[]{"100", "100", "0", "30"};
        }
        int[] inputNumbers = new int[args.length];

        // Parse the first two numbers to a positive integer
        for (int i = 0; i < 2; i++) {
            inputNumbers[i] = stringToPositiveInteger(args[i]);
        }

        // Parse the last two numbers to an integer
        for (int i = 2; i < args.length; i++) {
            inputNumbers[i] = stringToInteger(args[i]);
        }
        if (inputNumbers[0] < 0 || inputNumbers[1] < 0) {
            System.out.println("Invalid input. You should enter 4 integers."
                    + "\nThe first 2 must be non negative."
                    + "\nWe will adjust the ball to be within the screen.");
            inputNumbers[0] = 100;
            inputNumbers[1] = 100;
        }

        double centerX = inputNumbers[0];
        double centerY = inputNumbers[1];
        double dx = inputNumbers[2];
        double dy = inputNumbers[3];
        drawAnimation(new Point(centerX, centerY), dx, dy);
    }
}