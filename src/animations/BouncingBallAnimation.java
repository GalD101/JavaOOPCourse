package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import objects.Ball;
import objects.Point;

public class BouncingBallAnimation {
    /**
     * Checks if the string s is in a valid integer representation.
     *
     * @param str the string representing the number.
     * @return true iff the string is an integer.
     */
    private boolean isValidInteger(String str) {
        while ((str.charAt(0) == '-') || (str.charAt(0) == '+')) {
            if (str.length() == 1) {
                return false;
            }
            str = str.substring(1, str.length());
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }


    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",200,200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            // Negate the x velocity component when hitting the right || left wall
            if ((ball.getX() + (ball.getSize() + dx) > 200) || (ball.getX() - (ball.getSize() - dx) < 0)) {
                dx = -dx;
            }
            // Negate the y velocity component when hitting the top || bottom wall
            if ((ball.getY() + (ball.getSize() + dy) > 200) || (ball.getY() - (ball.getSize() - dy) < 0)) {
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
//        int[] nums = new int[args.length];
//        if (convertToInt(args)) {
//            // TODO: check input args
//        }
        double centerX = Integer.parseInt(args[0]);
        double centerY = Integer.parseInt(args[1]);
        double dx = Integer.parseInt(args[2]);
        double dy = Integer.parseInt(args[3]);
        drawAnimation(new Point(centerX, centerY), dx, dy);
    }
}
