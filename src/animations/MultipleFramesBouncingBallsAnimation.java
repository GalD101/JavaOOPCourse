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

        final int width = 1000;
        final int height = 600;
        final double P = 100.0; // Should not be bigger than 100! (Can cause jittering)
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
            balls[i] = new Ball(randomPoint.getX(), randomPoint.getY(), sizeOfBalls[i], randomColor);
        }

        // Second half
        for (int j = firstHalf.length; j < sizeOfBalls.length; j++) {
            // generate rand coordinates outside frame 1 and frame 2
            // :(((((((((((((((((((((((((((((((((((((((((((((((((((((((( FUCK I AM SO PISSED
            // I WORKED FOR SO LONG FOR NOTHING
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
//            Rectangle fuckScreen = new Rectangle(new Point(sizeOfBalls[i], 0), new Point(width, height), Color.WHITE);
            Point randomPoint = screen.generateRandomPointInside(sizeOfBalls[j]);
            if (frame1.getUpperLeft().getX() <= randomPoint.getX() && randomPoint.getX() <= frame1.getLowerRight().getX()) {
                randomPoint.setX((randomPoint.getX() + frame1.getWidth()) % (screen.getWidth() - sizeOfBalls[j]));
            }
            if (frame1.getUpperLeft().getY() <= randomPoint.getY() && randomPoint.getY() <= frame1.getLowerRight().getY()) {
                randomPoint.setY((randomPoint.getY() + frame1.getHeight()) % (screen.getHeight() - sizeOfBalls[j]));
            }
//            Point randomPoint = screen.generateRandomPointInside(frame1, sizeOfBalls[i]);
            balls[j] = new Ball(750  ,300, sizeOfBalls[j], randomColor);
        }

        for (Ball ball : balls) {
            if (ball == null) { continue; }
            if (ball.getSize() == 0) {
                System.err.println("Ball size cannot be zero.");
                System.exit(1);
            }
            double speed = ball.getSize() > 50 ? (P / 50) : (P / ball.getSize());
            // Balls with small radii and high speeds will jitter :(
            if (ball.getSize() < 10) {
                speed = P/10;
            }
            double angle = rand.nextDouble(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(frame1.getColor());
            d.fillRectangle((int) frame1.getUpperLeft().getX(), (int) frame1.getUpperLeft().getY(), (int) frame1.getWidth(), (int) frame1.getHeight());

            for (int n = 0; n < firstHalf.length; n++) {
                balls[n].collideWithFrameInside(frame1);
                balls[n].moveOneStep();
                balls[n].drawOn(d);
            }

            for (int k = firstHalf.length; k < balls.length; k++) {
                balls[k].collideWithFrameInside(screen);
//                Rectangle fuckFrame1 = new Rectangle(new Point(50- sizeOfBalls[i], 50 - sizeOfBalls[i]), new Point(500, 500), Color.GRAY);
//                Rectangle fuckFrame2 = new Rectangle(new Point(450 - sizeOfBalls[i], 450 - sizeOfBalls[i]), new Point(600, 600), Color.YELLOW);
                balls[k].collideWithFrameOutside(frame1);
                balls[k].collideWithFrameOutside(frame2);

                balls[k].moveOneStep();
                balls[k].drawOn(d);
            }

            d.setColor(frame2.getColor());
            d.fillRectangle((int) frame2.getUpperLeft().getX(), (int) frame2.getUpperLeft().getY(), (int) frame2.getWidth(), (int) frame2.getHeight());

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}