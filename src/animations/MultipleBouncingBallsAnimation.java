// 322558297 Gal Dali

package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;
import utils.RandomSingleton;

import java.awt.Color;
import java.util.Random;

import static utils.InputValidator.stringToInteger;

/**
 * This class is responsible for creating a multiple bouncing balls animation.
 * Each ball bounces off the edges of the screen.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The main method that creates an instance of MultipleBouncingBallsAnimation and calls the drawAnimation method.
     * It takes an array of string arguments, which represent the sizes of the balls.
     * Each ball is given a random color and a random initial position within the frame.
     * The velocity of each ball is calculated based on its size and a constant momentum value.
     * The balls are then animated within the frame,
     * with their velocities updated to simulate bouncing off the edges of the frame.
     *
     * @param args Command line arguments will take ball size as input.
     */
    public static void main(String[] args) {
        final int width = 750;
        final int height = 750;
        final int edges = 0;
        final int maxSize = 150;
        final double p = 1000.0; // Momentum of each ball.

        GUI gui = new GUI("Multiple bouncing balls animations", width, height);
        Sleeper sleeper = new Sleeper();
        Random rand = RandomSingleton.getInstance();

        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            // Strings that do not represent positive integers will be replaced with 0
            sizeOfBalls[i] = stringToInteger(args[i]);

            if (sizeOfBalls[i] > maxSize) {
                sizeOfBalls[i] = maxSize;
            }

            // Make each ball with a random color from the Color class.
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            // Create a ball with random position inside the frame.
            balls[i] = new Ball(utils.RandomSingleton.myNextDouble(sizeOfBalls[i], width - sizeOfBalls[i]),
                    utils.RandomSingleton.myNextDouble(
                            sizeOfBalls[i], height - sizeOfBalls[i]), sizeOfBalls[i], randomColor);
        }

        for (Ball ball : balls) {
            // Suppose each ball has a mass such that mass = size for simplicity.
            // Now each ball has the same momentum p = mass * velocity.
            // We can calculate the speed of each ball.
            double speed = p / ball.getSize();

            // Randomize the direction of the velocity.
            double angle = utils.RandomSingleton.myNextDouble(0, 360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                double dx = ball.getVelocity().getDx();
                double dy = ball.getVelocity().getDy();
                if ((ball.getX() + (ball.getSize() + dx) > width) || (ball.getX() - (ball.getSize() - dx) < edges)) {
                    dx = -dx;
                }
                if ((ball.getY() + (ball.getSize() + dy) > height) || (ball.getY() - (ball.getSize() - dy) < edges)) {
                    dy = -dy;
                }
                ball.setVelocity(dx, dy);
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}