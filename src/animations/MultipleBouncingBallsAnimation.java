package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;

import java.awt.Color;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {
        // TODO: Make the size of screen a constant! (remove hard coded values)
        GUI gui = new GUI("title",500,500);
        Sleeper sleeper = new Sleeper();

        Random rand = new Random(); // TODO: Use a constant random object for the ENTIRE project.
        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
//        if (convertToInt(args)) {
//            // TODO: check input args
//        }
        for (int i = 0; i < args.length; i++) {
            sizeOfBalls[i] = Integer.parseInt(args[i]);
            balls[i] = new Ball(250, 250, sizeOfBalls[i], Color.BLACK); // TODO: Make the initial center point random (according to instructions)
        }

        for (Ball ball : balls) {
            // TODO: Change this because I don't need else and I also plan to make this cooler by injecting some physics and the conservation of momentum! (inelastic collisions)
            if (ball.getSize() > 50) { // The numbers here are random, I will probably modify this later
                ball.setVelocity(rand.nextInt(10), rand.nextInt(10));
            } else {
                ball.setVelocity(9 * ball.getSize() / 10, 7 * ball.getSize() / 10);
            }
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                double dx = ball.getVelocity().getDx();
                double dy = ball.getVelocity().getDy();
                // TODO: Change hard coded values to constant values
                if ((ball.getX() + (ball.getSize() + dx) > 500) || (ball.getX() - (ball.getSize() - dx) < 0)) {
                    dx = -dx;
                }
                if ((ball.getY() + (ball.getSize() + dy) > 500) || (ball.getY() - (ball.getSize() - dy) < 0)) {
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