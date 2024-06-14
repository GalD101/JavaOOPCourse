// 322558297 Gal Dali

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import objects.Collidable;
import objects.Point;
import objects.Rectangle;
import objects.Block;
import objects.Ball;
import objects.Paddle;
import objects.Sprite;
import utils.RandomSingleton;

import java.awt.Color;

import static game.GameSettings.SCREEN_WIDTH;
import static game.GameSettings.SCREEN_HEIGHT;
import static game.GameSettings.SCREEN_BACKGROUND_COLOR;
import static game.GameSettings.SIDE_BLOCKS_TOP_WIDTH;
import static game.GameSettings.SIDE_BLOCKS_TOP_HEIGHT;
import static game.GameSettings.SIDE_BLOCKS_LEFT_WIDTH;
import static game.GameSettings.SIDE_BLOCKS_LEFT_HEIGHT;
import static game.GameSettings.SIDE_BLOCKS_RIGHT_WIDTH;
import static game.GameSettings.SIDE_BLOCKS_RIGHT_HEIGHT;
import static game.GameSettings.SIDE_BLOCKS_BOTTOM_WIDTH;
import static game.GameSettings.SIDE_BLOCKS_BOTTOM_HEIGHT;
import static game.GameSettings.SIDE_BLOCKS_FILL_COLOR;
import static game.GameSettings.MAIN_BLOCKS_WIDTH;
import static game.GameSettings.MAIN_BLOCKS_HEIGHT;
import static game.GameSettings.MAIN_BLOCKS_FILL_COLOR;
import static game.GameSettings.BALL_SIZE;
import static game.GameSettings.BALL_SPEED;
import static game.GameSettings.BALL_FILL_COLOR;
import static game.GameSettings.NUM_OF_BALLS;
import static game.GameSettings.PADDLE_WIDTH;
import static game.GameSettings.PADDLE_HEIGHT;
import static utils.MathUtils.computeAverage;

/**
 * The Game class represents a simple game with a ball, paddle, and blocks.
 * The game is played on a screen with a specific width and height.
 * The game has a SpriteCollection object to store and manage all the Sprite objects in the game,
 * and a GameEnvironment object to store and manage all the Collidable objects in the game.
 * The game has a GUI object to display the game on the screen, and a Sleeper object to control the game's timing.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Constructor for the Game class with parameters.
     *
     * <p>This constructor initializes a new Game object with the provided SpriteCollection and GameEnvironment.
     * The SpriteCollection is used to store and manage all the Sprite objects in the game,
     * such as the Ball and the Paddle.
     * The GameEnvironment is used to store and manage all the Collidable objects in the game,
     * and is used for collision detection and resolution.
     *
     * @param sprites     The SpriteCollection object to be associated with the game
     * @param environment The GameEnvironment object to be associated with the game
     */
    public Game(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
    }

    /**
     * Default constructor for the Game class.
     *
     * <p>This constructor initializes a new Game object with a new SpriteCollection and a new GameEnvironment.
     * The SpriteCollection is used to store and manage all the Sprite objects in the game,
     * such as the Ball and the Paddle.
     * The GameEnvironment is used to store and manage all the Collidable objects in the game,
     * and is used for collision detection and resolution.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Returns the game's SpriteCollection.
     *
     * <p>This method is used to retrieve the SpriteCollection object associated with the game.
     * The SpriteCollection object contains all the Sprite objects in the game,
     * and is used for drawing and updating the state of these objects.
     * Note that this method does not provide encapsulation,
     * as it returns the actual SpriteCollection object used by the game, not a copy of it.
     *
     * @return The SpriteCollection object associated with the game
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Returns the game's environment.
     *
     * <p>This method is used to retrieve the GameEnvironment object associated with the game.
     * The GameEnvironment object contains all the Collidable objects in the game,
     * and is used for collision detection and resolution.
     *
     * @return The GameEnvironment object associated with the game
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Adds a Collidable object to the game's environment.
     *
     * <p>This method is used to add a new Collidable to the game's GameEnvironment.
     * The Collidable could be any object that implements the Collidable interface, such as a Block or a Paddle.
     * Once added, the Collidable will be considered in collision detection and resolution.
     *
     * @param c The Collidable object to be added to the game's environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a Sprite object to the game.
     *
     * <p>This method is used to add a new Sprite to the game's SpriteCollection.
     * The Sprite could be any graphical object that implements the Sprite interface, such as a Ball or a Paddle.
     * Once added, the Sprite will be included in the game's animation loop,
     * and its drawOn and timePassed methods will be called in each iteration of the loop.
     *
     * @param s The Sprite object to be added to the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game by creating the Blocks, Ball, and Paddle, and adding them to the game.
     *
     * <p>This method sets up the game environment by doing the following:
     * 1. Defines the height and width of the game screen.
     * 2. Creates a new GUI with the specified dimensions.
     * 3. Initializes a Sleeper object for controlling the game's timing.
     * 4. Defines the size and speed of the game ball.
     * 5. Defines the width of the blocks and the separation between them.
     * 6. Adds the screen boundaries to the game.
     * 7. Creates and adds blocks to the game until the width of the game screen is filled.
     * 8. Creates the game ball with a fixed speed and random direction, and adds it to the game.
     * 9. Creates the paddle and adds it to the game.
     */
    public void initialize() {
        this.gui = new GUI("G.L.D.223", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.sleeper = new Sleeper();

        /*               SIDE BLOCKS               */
        Block leftSideBlock = new Block(new Rectangle(
                new Point(0, 0 + SIDE_BLOCKS_TOP_HEIGHT),
                SIDE_BLOCKS_LEFT_WIDTH, SIDE_BLOCKS_LEFT_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR);
        Block topSideBlock = new Block(new Rectangle(
                new Point(0, 0),
                SIDE_BLOCKS_TOP_WIDTH, SIDE_BLOCKS_TOP_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR);
        Block rightSideBlock = new Block(new Rectangle(
                new Point(SCREEN_WIDTH - SIDE_BLOCKS_RIGHT_WIDTH, SIDE_BLOCKS_TOP_HEIGHT),
                SIDE_BLOCKS_RIGHT_WIDTH, SIDE_BLOCKS_RIGHT_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR);
        Block bottomSideBlock = new Block(new Rectangle(
                new Point(SIDE_BLOCKS_LEFT_WIDTH, SCREEN_HEIGHT - SIDE_BLOCKS_BOTTOM_HEIGHT),
                SIDE_BLOCKS_BOTTOM_WIDTH, SIDE_BLOCKS_BOTTOM_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR);

        leftSideBlock.addToGame(this);
        topSideBlock.addToGame(this);
        rightSideBlock.addToGame(this);
        bottomSideBlock.addToGame(this);

        /*               MAIN_BLOCKS               */
        final double separationBetweenBlocks = 0;
        final double separationStart = 3 * MAIN_BLOCKS_WIDTH;
        final double blocksYValue = 0.1 * SCREEN_HEIGHT + MAIN_BLOCKS_HEIGHT;

        double x = MAIN_BLOCKS_HEIGHT + separationStart;
        final double numOfRows = 6;
        for (int i = 0; i < numOfRows; i++) {
            createLineOfBlocks(separationBetweenBlocks, x + i * MAIN_BLOCKS_WIDTH,
                    blocksYValue + i * MAIN_BLOCKS_HEIGHT, MAIN_BLOCKS_FILL_COLOR[i]);
        }

        /*               GAME BALL               */
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            // Create the game ball and position it in the lower middle part of the screen
            Point gameBallCenterPoint = new Point(computeAverage(0, SCREEN_WIDTH), 0.7 * SCREEN_HEIGHT);
            Ball gameBall = new Ball(gameBallCenterPoint, BALL_SIZE, BALL_FILL_COLOR, this.environment);
            // give the ball fixed speed and random direction
            gameBall.setVelocity(animations.Velocity.fromAngleAndSpeed(
                    RandomSingleton.myNextDouble(0, 360), BALL_SPEED));
            gameBall.addToGame(this);
        }

        /*               PADDLE               */
        Rectangle gamePaddleRectangle = new Rectangle(
                new Point(computeAverage(0 + MAIN_BLOCKS_HEIGHT, SCREEN_WIDTH - MAIN_BLOCKS_HEIGHT),
                        SCREEN_HEIGHT - 2 * MAIN_BLOCKS_HEIGHT + 4), PADDLE_WIDTH, PADDLE_HEIGHT);
        Paddle gamePaddle = new Paddle(this.gui.getKeyboardSensor(), gamePaddleRectangle);
        gamePaddle.addToGame(this);
    }

    private void createLineOfBlocks(double separationBetweenBlocks, double startXValue,
                                    double blocksYValue, Color color) {
        while (startXValue < SCREEN_WIDTH - GameSettings.MAIN_BLOCKS_HEIGHT) {
            Block block = new Block(new Rectangle(
                    new Point(startXValue, blocksYValue), MAIN_BLOCKS_WIDTH, GameSettings.MAIN_BLOCKS_HEIGHT), color);
            startXValue += MAIN_BLOCKS_WIDTH + separationBetweenBlocks;
            block.addToGame(this);
        }
    }

    /**
     * Runs the game by starting the animation loop.
     *
     * <p>This method starts an infinite loop that represents the game's main animation loop.
     * In each iteration of the loop, it does the following:
     * 1. Records the current time for timing purposes.
     * 2. Gets the DrawSurface from the GUI.
     * 3. Calls the drawAllOn method on the sprites, which draws all the sprites on the DrawSurface.
     * 4. Shows the DrawSurface on the GUI.
     * 5. Calls the notifyAllTimePassed method on the sprites, which updates the state of all the sprites.
     * 6. Calculates the time used in the current iteration of the loop.
     * 7. If there is any time left in the current frame
     * (based on the desired frames per second), it puts the thread to sleep for that amount of time.
     *
     * <p>The desired frames per second is set to 60,
     * which means the target time for each iteration of the loop is approximately 16.67 milliseconds.
     */
    public void run() {
        // Start the animation loop
        // Go over all the sprites and call drawOn() and timePassed() on each one
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            // Draw background
            d.setColor(SCREEN_BACKGROUND_COLOR);
            d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            // Draw sprites
            this.sprites.drawAllOn(d);

            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
