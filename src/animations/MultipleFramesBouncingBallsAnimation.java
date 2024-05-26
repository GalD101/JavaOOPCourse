package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;
import objects.Point;
import utils.RandomSingleton;

import java.awt.Color;
import java.util.Random;

import static utils.InputValidator.stringToInteger;

//TODO: Handle cases of no input args!!!!!!! (IN ALL CLASSES WHERE THIS IS RELEVANT!!!!)
public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {
        // In this class, I need to create a 'frame'. Aframe will be like a subsection of the screen where the balls will be bouncing
        // off of. There will be balls outside the frame and inside the frame. The balls inside the frame will bounce off the walls of the frame.
        // The first frame will start from (50, 50) and end at (500, 500) and it will be a grey rectangle (use fillRectangle method from biuoop.DrawSurface class).
        // The second frame will be yellow and will start at (450, 450) and end at (600, 600). We want the first half of the balls to bounce inside the grey rectangle, and the second half of the balls
        // to bounce outside both rectangles.
        final int width = 800;
        final int height = 600;
        final double P = 100.0; // Momentum of each ball.
        final Color frame1Color = Color.GRAY;
        final Color frame2Color = Color.YELLOW;
        Point frame1Start = new Point(50, 50);
        Point frame1End = new Point(500, 500);
        Point frame2Start = new Point(450, 450);
        Point frame2End = new Point(600, 600);

        GUI gui = new GUI("Multiple frames bouncing balls animation", width, height);
        Sleeper sleeper = new Sleeper();
        Random rand = RandomSingleton.getInstance();

        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            sizeOfBalls[i] = stringToInteger(args[i]);
        }

        // First half
        for (int i = 0; i < (int) (sizeOfBalls.length / 2); i++) {
            double randomX;
            double randomY;
            // TODO: use intersection function for rectangle I think
            do {
                randomX = rand.nextDouble(frame1Start.getX() + sizeOfBalls[i], frame1End.getX() - sizeOfBalls[i]);
                randomY = rand.nextDouble(frame1Start.getY() + sizeOfBalls[i], frame1End.getY() - sizeOfBalls[i]);
            } while (randomX + sizeOfBalls[i] > frame2Start.getX() && randomY + sizeOfBalls[i] < frame2Start.getY()); // position is inside the second frame so generate again
            balls[i] = new Ball(randomX, randomY, sizeOfBalls[i], Color.BLACK);
        }

        // Second half
        for (int i = (int) (sizeOfBalls.length / 2); i < sizeOfBalls.length; i++) {
            double x = rand.nextDouble(0, width);
            do {
                // generate random number on the screen
                x = rand.nextDouble(0, width);
            } while (/*x is not inside the two rectangles*/false);//TODO: Do the same for y coordinate
            balls[i] = new Ball(rand.nextDouble(frame2Start.getX() + sizeOfBalls[i], frame2End.getX() - sizeOfBalls[i]), rand.nextDouble(frame2Start.getY() + sizeOfBalls[i], frame2End.getY() - sizeOfBalls[i]), sizeOfBalls[i], Color.BLACK);
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
            // color the frames:
            // frame 1:
            d.setColor(frame1Color);
            d.fillRectangle((int) Math.round(frame1Start.getX()), (int) Math.round(frame1Start.getY()), (int) Math.round(frame1End.getX() - frame1Start.getX()),
                    (int) Math.round(frame1End.getY() - frame1Start.getY()));

            // frame 2:
            d.setColor(frame2Color);
            d.fillRectangle((int) Math.round(frame2Start.getX()), (int) Math.round(frame2Start.getY()), (int) Math.round(frame2End.getX() - frame2Start.getX()),
                    (int) Math.round(frame2End.getY() - frame2Start.getY()));

            // first half
            for (int i = 0; i < (int) (balls.length / 2); i++) {
                double dx = balls[i].getVelocity().getDx();
                double dy = balls[i].getVelocity().getDy();

                if ((balls[i].getX() + (balls[i].getSize() + dx) > frame1End.getX()) || (balls[i].getX() - (balls[i].getSize() - dx) < frame1Start.getX())) {
                    dx = -dx;
                }
                if ((balls[i].getX() + (balls[i].getSize() + dx) > frame2Start.getX()) && (balls[i].getY() + balls[i].getSize() > frame2Start.getY())) {
                    dx = -dx;
                }
                if ((balls[i].getY() + (balls[i].getSize() + dy) > frame2Start.getY()) && (balls[i].getX() + balls[i].getSize() > frame2Start.getY())) {
                    dy = -dy;
                }
                if ((balls[i].getY() + (balls[i].getSize() + dy) > frame1End.getY()) || (balls[i].getY() - (balls[i].getSize() - dy) < frame1Start.getY())) {
                    dy = -dy;
                }
                balls[i].setVelocity(dx, dy);
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }

            // second half // TODO
            for (int i = (int) (balls.length / 2); i < balls.length; i++) {
                double dx = balls[i].getVelocity().getDx();
                double dy = balls[i].getVelocity().getDy();

                if ((balls[i].getX() + (balls[i].getSize() + dx) > width) || (balls[i].getX() - (balls[i].getSize() - dx) < 0)) {
                    dx = -dx;
                }
                if ((balls[i].getY() + (balls[i].getSize() + dy) > height) || (balls[i].getY() - (balls[i].getSize() - dy) < 0)) {
                    dy = -dy;
                }
                balls[i].setVelocity(dx, dy);
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
