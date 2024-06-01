package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;
import objects.Point;
import objects.Rectangle;
import utils.RandomSingleton;
import utils.Threshold;

import java.awt.Color;
import java.util.Random;

import static utils.InputValidator.stringToInteger;

public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {
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
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            Point randPoint = new Point(rand.nextDouble(sizeOfBalls[j] + frame2.getLowerRight().getX(), width - sizeOfBalls[j]),
                    rand.nextDouble(sizeOfBalls[j], height - sizeOfBalls[j]));
            // TODO
//            if (sizeOfBalls[j] < frame1.getUpperLeft().getY()) {
//                if (rand.nextBoolean()) {
//                    randPoint.setY();
//                }
//            }
            if (randPoint.getY() + sizeOfBalls[j] < frame2.getUpperLeft().getY()) {
                randPoint.setX(rand.nextDouble(frame1.getLowerRight().getX() + sizeOfBalls[j],
                        width - sizeOfBalls[j]));
            }

            if (sizeOfBalls[j] < frame1.getUpperLeft().getX() - sizeOfBalls[j]) {
                if (rand.nextBoolean()) {
                    randPoint = new Point(rand.nextDouble(sizeOfBalls[j], frame1.getUpperLeft().getX() - sizeOfBalls[j]),
                            rand.nextDouble(sizeOfBalls[j], height - sizeOfBalls[j]));
                }
            }
            if (sizeOfBalls[j] < height - frame1.getLowerRight().getY() - sizeOfBalls[j]) {
                if (rand.nextBoolean()) {
                    randPoint = new Point(rand.nextDouble(sizeOfBalls[j], frame2.getUpperLeft().getX() - sizeOfBalls[j]),
                            rand.nextDouble(sizeOfBalls[j] + frame1.getLowerRight().getY(), height - sizeOfBalls[j]));
                }
            }
                balls[j] = new Ball(randPoint.getX(), randPoint.getY(), sizeOfBalls[j], randomColor);
        }

        for (Ball ball : balls) {
            double speed = ball.getSize() > 50 ? (P / 50) : (P / ball.getSize());
            // Balls with small radius and high speeds will jitter :(
            if (ball.getSize() < 10) {
                speed = P / 10;
            }
            double angle = rand.nextDouble(0, 360);
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