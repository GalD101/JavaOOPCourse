import biuoop.GUI;
import biuoop.DrawSurface;
import objects.Ball;
import objects.Point;

public class BallsTest1 {
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
        drawAnimation(new Point(100,100), 0, +30);
    }
}