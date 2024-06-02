// 322558297 Gal Dali
package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;
import objects.Point;
import objects.Rectangle;
import utils.RandomSingleton;

import java.awt.Color;
import java.util.Random;

import static utils.InputValidator.stringToPositiveInteger;

/**
 * This class represents a multiple frames bouncing balls animation.
 * The animation includes two frames and balls bouncing within and outside these frames .
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * The main method of the class.
     * It initialized the GUI, creates the frames and balls, and runs the animation.
     * @param args The command line arguments, which represent the sizes of the balls.
     */
    public static void main(String[] args) {
        // Screen dimensions
        final int screenWidth = 1000;
        final int screenHeight = 600;

        // The momentum of each ball. Should not be bigger than 100! (Can cause jittering)
        final double p = 100.0;
        final int maxBallSize = 199;

        // Create instances of the screen and frames
        Rectangle screen = new Rectangle(new Point(0, 0), new Point(screenWidth, screenHeight), Color.WHITE);
        Rectangle frame1 = new Rectangle(new Point(50, 50), new Point(500, 500), Color.GRAY);
        Rectangle frame2 = new Rectangle(new Point(450, 450), new Point(600, 600), Color.YELLOW);

        // Initialize Gui, Sleeper and random instance
        GUI gui = new GUI("Multiple frames bouncing balls animation", screenWidth, screenHeight);
        Sleeper sleeper = new Sleeper();
        Random rand = RandomSingleton.getInstance();

        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
        int numBallsInFrame1 = (int) (sizeOfBalls.length / 2);

        // Parse input arguments to integers
        for (int i = 0; i < args.length; i++) {
            sizeOfBalls[i] = stringToPositiveInteger(args[i]);
            if (sizeOfBalls[i] < 0) {
                System.out.println(args[i] + " is an invalid input and it will be treated as 0."
                        + "\nYou should enter an even number of positive integer seperated by spaces."
                        + "\nWe will treat your input as 0");
                sizeOfBalls[i] = 0;
            }

            // If ball is too big, set it to the maximum size.
            if (sizeOfBalls[i] > maxBallSize) {
                sizeOfBalls[i] = maxBallSize;
            }
        }

        // generate randomly positioned balls inside frame 1:
        for (int i = 0; i < numBallsInFrame1; i++) {
            Point randomPointInFrame1 = frame1.generateRandomPointInside(sizeOfBalls[i]);
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            balls[i] = new Ball(randomPointInFrame1.getX(), randomPointInFrame1.getY(), sizeOfBalls[i], randomColor);
        }

        // generate randomly positioned balls inside the screen, but outside the frames:
        for (int j = numBallsInFrame1; j < sizeOfBalls.length; j++) {
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            double randX = utils.RandomSingleton.myNextDouble(
                    sizeOfBalls[j] + frame2.getLowerRight().getX(), screenWidth - sizeOfBalls[j]);
            double randY = utils.RandomSingleton.myNextDouble(
                    sizeOfBalls[j], screenHeight - sizeOfBalls[j]);
            Point randomPointOutsideFrames;
            int randomDomain = 1 + rand.nextInt(6);
            switch (randomDomain) {
                // Upper section
                case 1:
                    if (sizeOfBalls[j] < frame1.getUpperLeft().getY() - sizeOfBalls[j]) {
                        randX = utils.RandomSingleton.myNextDouble(
                                sizeOfBalls[j], frame1.getLowerRight().getX() - sizeOfBalls[j]);
                        randY = utils.RandomSingleton.myNextDouble(
                                sizeOfBalls[j], frame1.getUpperLeft().getY() - sizeOfBalls[j]);
                    }
                    break;
                // Left section
                case 2:
                    if (sizeOfBalls[j] < frame1.getUpperLeft().getX() - sizeOfBalls[j]) {
                        randX = utils.RandomSingleton.myNextDouble(
                                sizeOfBalls[j], frame1.getUpperLeft().getX() - sizeOfBalls[j]);
                        randY = utils.RandomSingleton.myNextDouble(
                                sizeOfBalls[j], screenHeight - sizeOfBalls[j]);
                    }
                    break;
                // Bottom section
                case 3:
                    if (sizeOfBalls[j] < screenHeight - frame1.getLowerRight().getY() - sizeOfBalls[j]) {
                        randX = utils.RandomSingleton.myNextDouble(
                                sizeOfBalls[j], frame2.getUpperLeft().getX() - sizeOfBalls[j]);
                        randY = utils.RandomSingleton.myNextDouble(
                                sizeOfBalls[j] + frame1.getLowerRight().getY(), screenHeight - sizeOfBalls[j]);
                    }
                    break;
                // Right section
                case 4:
                    randX = utils.RandomSingleton.myNextDouble(
                            sizeOfBalls[j] + frame2.getLowerRight().getX(), screenWidth - sizeOfBalls[j]);
                    randY = utils.RandomSingleton.myNextDouble(
                            sizeOfBalls[j], screenHeight - sizeOfBalls[j]);
                    break;
                // Upper right section
                case 5:
                    randX = utils.RandomSingleton.myNextDouble(
                            sizeOfBalls[j] + frame1.getLowerRight().getX(), screenWidth - sizeOfBalls[j]);
                    randY = utils.RandomSingleton.myNextDouble(
                            sizeOfBalls[j], frame2.getUpperLeft().getY() - sizeOfBalls[j]);
                    break;
                default:
                    break;
            }
            randomPointOutsideFrames = new Point(randX, randY);
            balls[j] = new Ball(randomPointOutsideFrames.getX(), randomPointOutsideFrames.getY(),
                    sizeOfBalls[j], randomColor);
        }

        // Set a random velocity for each ball according to it's size
        for (Ball ball : balls) {
            // Balls with size > 50 will all have the same slow speed.
            double speed = p / Math.max(50, ball.getSize());

            // Balls with small radius and high speeds will jitter; so we set a speed limit
            if (ball.getSize() < 10) {
                speed = p / 10;
            }
            double angle = utils.RandomSingleton.myNextDouble(0, 360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
        }

        // Main animation loop
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(frame1.getColor());
            d.fillRectangle((int) frame1.getUpperLeft().getX(), (int) frame1.getUpperLeft().getY(),
                    (int) frame1.getWidth(), (int) frame1.getHeight());

            for (int n = 0; n < numBallsInFrame1; n++) {
                balls[n].collideWithFrameInside(frame1);
                balls[n].moveOneStep();
                balls[n].drawOn(d);
            }

            for (int k = numBallsInFrame1; k < balls.length; k++) {
                balls[k].collideWithFrameInside(screen);
                balls[k].collideWithFrameOutside(frame1);
                balls[k].collideWithFrameOutside(frame2);
                balls[k].moveOneStep();
                balls[k].drawOn(d);
            }

            // frame 2 should hide all balls and therefor, we create it last
            d.setColor(frame2.getColor());
            d.fillRectangle((int) frame2.getUpperLeft().getX(), (int) frame2.getUpperLeft().getY(),
                    (int) frame2.getWidth(), (int) frame2.getHeight());

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
