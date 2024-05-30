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

import static utils.InputValidator.stringToInteger;

public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided. Please provide the sizes of the balls as command-line arguments.");
            System.exit(1);
        }

        final int width = 800;
        final int height = 600;
        final double P = 100.0;
        Rectangle screen = new Rectangle(new Point(0, 0), new Point(width, height), Color.WHITE);
        Rectangle frame1 = new Rectangle(new Point(50, 50), new Point(500, 500), Color.GRAY);
        Rectangle frame2 = new Rectangle(new Point(450, 450), new Point(600, 600), Color.YELLOW);

        GUI gui = new GUI("Multiple frames bouncing balls animation", width, height);
        Sleeper sleeper = new Sleeper();
        Random rand = RandomSingleton.getInstance();

        int[] sizeOfBalls = new int[args.length];
        Ball[] balls = new Ball[args.length];
        Ball[] firstHalf = new Ball[(int) (sizeOfBalls.length / 2)];
        Ball[] secondHalf = new Ball[sizeOfBalls.length - firstHalf.length];
        for (int i = 0; i < args.length; i++) {
            sizeOfBalls[i] = stringToInteger(args[i]);
        }

        // First half
        // generate rand coordinates inside frame 1
        for (int i = 0; i < firstHalf.length; i++) {
            Point randomPoint = frame1.generateRandomPointInside(sizeOfBalls[i]);
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
//            double randomX;
//            double randomY;
//            do {
//                randomX = rand.nextDouble(frame1Start.getX() + sizeOfBalls[i], frame1End.getX() - sizeOfBalls[i]);
//                randomY = rand.nextDouble(frame1Start.getY() + sizeOfBalls[i], frame1End.getY() - sizeOfBalls[i]);
//            } while (randomX + sizeOfBalls[i] > frame2Start.getX() && randomY + sizeOfBalls[i] < frame2Start.getY());
            balls[i] = new Ball(randomPoint.getX(), randomPoint.getY(), sizeOfBalls[i], Color.BLACK);
        }

        // Second half
        for (int i = firstHalf.length; i < sizeOfBalls.length; i++) {
            // generate rand coordinates outside frame 1 and frame 2
            Point randomPoint = screen.generateRandomPointInside(frame1, frame2, sizeOfBalls[i]);
            balls[i] = new Ball(randomPoint.getX(), randomPoint.getY(), sizeOfBalls[i], Color.RED);
        }

        for (Ball ball : balls) {
            if (ball.getSize() == 0) {
                System.err.println("Ball size cannot be zero.");
                System.exit(1);
            }
            double speed = P / ball.getSize();
            double angle = rand.nextDouble(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(frame1.getColor());
            d.fillRectangle((int) frame1.getUpperLeft().getX(), (int) frame1.getUpperLeft().getY(), (int) frame1.getWidth(), (int) frame1.getHeight());

            d.setColor(frame2.getColor());
            d.fillRectangle((int) frame2.getUpperLeft().getX(), (int) frame2.getUpperLeft().getY(), (int) frame2.getWidth(), (int) frame2.getHeight());


            for (int i = 0; i < balls.length; i++) {
//                double dx = balls[i].getVelocity().getDx();
//                double dy = balls[i].getVelocity().getDy();
//
//                if ((balls[i].getX() + (balls[i].getSize() + dx) > frame1End.getX()) || (balls[i].getX() - (balls[i].getSize() - dx) < frame1Start.getX())) {
//                    dx = -dx;
//                }
//                if ((balls[i].getX() + (balls[i].getSize() + dx) > frame2Start.getX()) && (balls[i].getY() + balls[i].getSize() > frame2Start.getY())) {
//                    dx = -dx;
//                }
//                if ((balls[i].getY() + (balls[i].getSize() + dy) > frame2Start.getY()) && (balls[i].getX() + balls[i].getSize() > frame2Start.getY())) {
//                    dy = -dy;
//                }
//                if ((balls[i].getY() + (balls[i].getSize() + dy) > frame1End.getY()) || (balls[i].getY() - (balls[i].getSize() - dy) < frame1Start.getY())) {
//                    dy = -dy;
//                }
//                balls[i].setVelocity(dx, dy);
//                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}