package animations;

import biuoop.GUI;
import objects.Ball;

import java.util.Random; // TODO: Use A constant Random object for the ENTIRE project.

public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {
        GUI gui = new GUI("title",200,200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        Random rand = new Random();
        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
        //        if (convertToInt(args)) {
//            // TODO: check input args
//        }
        for (int i = 0; i < args.length; i++) {
            sizeOfBalls[i] = Integer.parseInt(args[i]);
        }
        for (int i = 0; i < sizeOfBalls.length; i++) {
            balls[i] = new Ball(sizeOfBalls[i], rand.nextInt(200), rand.nextInt(200), java.awt.Color.BLACK);
            // TODO: Change this cause I dont need else and in general I plan to make this a bit cooler using physics and the conservation of momentum! (inelastic collisions)
            if (balls[i].getSize() > 50) {
                balls[i].setVelocity(25, 25);
            } else {
                balls[i].setVelocity(50, 50);
            }
            balls[i].setVelocity(rand.nextInt(50), rand.nextInt(50));
        }
    }
}
