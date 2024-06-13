import animations.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.GameEnvironment;
import objects.Block;
import objects.Collidable;
import objects.Point;
import objects.Rectangle;
import objects.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Main class serves as the entry point for the application.
 * It contains the main method which initializes the game environment,
 * creates blocks and balls, and starts the game loop.
 */
public class Main {
    /**
     * The main entry point for the application.
     * This method initializes the game environment, creates blocks and balls, and starts the game loop.
     *
     * @param args The command-line arguments. This is currently unused.
     */
    public static void main(String[] args) {
        Random rnd = new Random();
        int rectsAmount = 7;
        final int height = 600;
        final int width = 800;
        GUI gui = new GUI("title", width, height);
        GameEnvironment ge = new GameEnvironment();
        List<Collidable> blocks = new ArrayList<>();
        for (int i = 0; i < rectsAmount; i++) {
            for (int j = 0; j < rectsAmount; j++) {
                Block myBlock = new Block(new Rectangle(
                        new Point(10 + i * 80, 10 + j * 60), 20, 20), java.awt.Color.BLACK, ge);
                blocks.add(myBlock);
                ge.addCollidable(myBlock);
            }
        }
        Block rightSideOfScreen = new Block(new Rectangle(
                new Point(width, 0), width, height), java.awt.Color.BLACK, ge);
        Block leftSideOfScreen = new Block(new Rectangle(
                new Point(-width, 0), new Point(0, height)), java.awt.Color.BLACK, ge);
        Block topSideOfScreen = new Block(new Rectangle(
                new Point(0, -height), new Point(width, 0)), java.awt.Color.BLACK, ge);
        Block bottomSideOfScreen = new Block(new Rectangle(
                new Point(0, height), new Point(width, 2 * height)), java.awt.Color.BLACK, ge);
        ge.addCollidable(rightSideOfScreen);
        ge.addCollidable(leftSideOfScreen);
        ge.addCollidable(topSideOfScreen);
        ge.addCollidable(bottomSideOfScreen);
        Ball[] balls = new Ball[rectsAmount * rectsAmount];
        for (int i = 0; i < rectsAmount; i++) {
            for (int j = 0; j < rectsAmount; j++) {
                balls[j + i * rectsAmount] = new Ball(new Point(500, 500), 5, java.awt.Color.RED, ge);
                balls[j + i * rectsAmount].setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt(0, 360), 5));
            }
        }
        while (true) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(20);
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < rectsAmount; i++) {
                for (int j = 0; j < rectsAmount; j++) {
                    d.setColor(Color.black);
                    d.fillRectangle((int) blocks.get(j + i * rectsAmount).getCollisionRectangle().getUpperLeft().getX(),
                            (int) blocks.get(j + i * rectsAmount).getCollisionRectangle().getUpperLeft().getY(),
                            (int) blocks.get(j + i * rectsAmount).getCollisionRectangle().getWidth(),
                            (int) blocks.get(j + i * rectsAmount).getCollisionRectangle().getHeight());
                    d.setColor(Color.red);
                    d.fillCircle(
                            (int) balls[j + i * rectsAmount].getCenter().getX(),
                            (int) balls[j + i * rectsAmount].getCenter().getY(),
                            balls[j + i * rectsAmount].getSize());
                    balls[j + i * rectsAmount].getGameEnvironment();
                    balls[j + i * rectsAmount].moveOneStep();
                }
            }
            gui.show(d);


        }

    }
}
