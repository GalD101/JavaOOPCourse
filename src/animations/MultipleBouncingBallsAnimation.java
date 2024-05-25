package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;
import utils.RandomSingleton;
import java.awt.Color;
import java.util.Random;

import static utils.InputValidator.stringToInteger;

public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {
        final int WIDTH = 1000;
        final int HEIGHT = 1000;
        final int EDGES = 0;
        final double P = 1000.0; // Momentum of each ball.
        GUI gui = new GUI("Multiple bouncing balls animations", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        Random rand = RandomSingleton.getInstance();
        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            // Strings that do not represent positive integers will be replaced with 0
            sizeOfBalls[i] = stringToInteger(args[i]);

            // Make each ball with a random color from the Color class.
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

            // Create a ball with random position inside the frame.
            balls[i] = new Ball(rand.nextDouble(sizeOfBalls[i], WIDTH - sizeOfBalls[i]), rand.nextDouble(sizeOfBalls[i], HEIGHT - sizeOfBalls[i]), sizeOfBalls[i], randomColor);
        }

        for (Ball ball : balls) {
            // Suppose each ball has a mass such that mass = size for simplicity.
            // Now each ball has the same momentum p = mass * velocity.
            // We can calculate the speed of each ball.
            double speed = P / ball.getSize();

            // Randomize the direction of the velocity.
            double angle = rand.nextDouble(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                double dx = ball.getVelocity().getDx();
                double dy = ball.getVelocity().getDy();
                if ((ball.getX() + (ball.getSize() + dx) > WIDTH) || (ball.getX() - (ball.getSize() - dx) < EDGES)) {
                    dx = -dx;
                }
                if ((ball.getY() + (ball.getSize() + dy) > HEIGHT) || (ball.getY() - (ball.getSize() - dy) < EDGES)) {
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