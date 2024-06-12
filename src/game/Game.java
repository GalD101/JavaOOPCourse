package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Ball;
import objects.Block;
import objects.Point;
import objects.Collidable;
import objects.Rectangle;
import objects.Sprite;
import utils.RandomSingleton;

import java.awt.*;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    public Game(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
    }

    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public SpriteCollection getSprites() {
        return this.sprites; // No encapsulation because I want to use the same copy!
    }

    public GameEnvironment getEnvironment() {
        return this.environment; // No encapsulation because I want to use the same copy!
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    // TODO: Use run to make sure this is OK
    public void initialize() {
        final int height = 600;
        final int width = 800;
        final int ballSize = 5; // NOTE: This is the size of the ball. it must be relatively small since we assume the ball to be a point object
        final int ballSpeed = 5;
        this.gui = new GUI("G.L.D.223", width, height);
        this.sleeper = new Sleeper();

        // TODO: Check that this really looks OK - it does ;)
        final double blockWidth = 50;
        final double seperationBetweenBlocks = 25;
        int x = 0;
        double blocksYValue = 0.25 * height;
        addScreenBounderies(width, height);
        while (x < width) {
            Block block = new Block(new Rectangle(new Point(x, blocksYValue), blockWidth, 20), Color.green, this.environment);
            x += blockWidth + seperationBetweenBlocks;
            block.addToGame(this);
        }

        // Create the game ball and position it in the lower middle part of the screen with fixed speed and random direction
        Point gameBallCenterPoint = new Point(utils.MathUtils.computeAverage(0, width), 0.69 * height);
        Ball gameBall = new Ball(gameBallCenterPoint, ballSize, Color.BLUE, this.environment);
        gameBall.setVelocity(animations.Velocity.fromAngleAndSpeed(RandomSingleton.myNextDouble(0, 360), ballSpeed));
        gameBall.addToGame(this);

        // Here I need to create the GUI - check, add blocks to the screen - check but needs testing, add the ball - check and the paddle - will happen in the future. and any other game object
        // Size of GUI should be 800x600 (width x height) - check
        // Create the Blocks - check needs testing
        // Create the Ball - check
        // Create the Paddle - will be done in the future
        // Add the Blocks, Ball, and Paddle to the game - check

    }

    private void addScreenBounderies(double width, double height) {
        Block rightSideOfScreen = new Block(new Rectangle(new Point(width, 0), width, height), java.awt.Color.BLACK);
        Block leftSideOfScreen = new Block(new Rectangle(new Point(-width, 0), new Point(0, height)), java.awt.Color.BLACK);
        Block topSideOfScreen = new Block(new Rectangle(new Point(0, -height), new Point(width, 0)), java.awt.Color.BLACK);
        Block bottomSideOfScreen = new Block(new Rectangle(new Point(0, height), new Point(width, 2*height)), java.awt.Color.BLACK);
        rightSideOfScreen.addToGame(this);
        leftSideOfScreen.addToGame(this);
        topSideOfScreen.addToGame(this);
        bottomSideOfScreen.addToGame(this);
    }

    // Run the game -- start the animation loop.
    public void run() {
        // Start the animation loop
        // Go over all the sprites and call drawOn() and timePassed() on each one
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
