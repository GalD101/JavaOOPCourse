package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import objects.Ball;
import objects.Point;

import static utils.InputValidator.stringToInteger;

public class BouncingBallAnimation {
    static private void drawAnimation(Point start, double dx, double dy) {
        final int WIDTH = 200;
        final int HEIGHT = 200;
        final int EDGES = 0;
        final int RADIUS = 30;
        GUI gui = new GUI("Bouncing ball animation", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        Ball ball = new Ball(start.getX(), start.getY(), RADIUS, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            // Negate the x velocity component when hitting the right || left wall
            if ((ball.getX() + (ball.getSize() + dx) > WIDTH) || (ball.getX() - (ball.getSize() - dx) < EDGES)) {
                dx = -dx;
            }
            // Negate the y velocity component when hitting the top || bottom wall
            if ((ball.getY() + (ball.getSize() + dy) > HEIGHT) || (ball.getY() - (ball.getSize() - dy) < EDGES)) {
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

    public static void main(String[] args) {
        // Check if the input is valid
        if (args.length != 4) {
            System.out.println("Usage: BouncingBallAnimation <centerX> <centerY> <dx> <dy>");
            System.exit(1);
        }
        int[] inputNumbers = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            inputNumbers[i] = stringToInteger(args[i]);
        }

        // Adjust the ball to be within the screen
        // TODO: Use a constant for the radius of the ball.
        double centerX = inputNumbers[0] > 30 ? inputNumbers[0] : 30 + inputNumbers[0];
        double centerY = inputNumbers[1] > 30 ? inputNumbers[1] : 30 + inputNumbers[1];
        double dx = inputNumbers[2];
        double dy = inputNumbers[3];
        drawAnimation(new Point(centerX, centerY), dx, dy);
    }
}
