// 322558297 Gal Dali

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import events.BallRemover;
import events.BlockRemover;
import events.HitListener;
import events.ScoreTrackingListener;
import objects.*;
import utils.Counter;
import utils.RandomSingleton;

import java.awt.Color;
import java.util.List;

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
    private List<HitListener> hitListeners; //TODO: Change this
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private boolean gameOver = false;

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
        this.hitListeners = new java.util.ArrayList<>();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter(NUM_OF_BALLS);
        this.score = new Counter();
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
        this.hitListeners = new java.util.ArrayList<>();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter(NUM_OF_BALLS);
        this.score = new Counter();
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

        /*               EVENT LISTENERS               */
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
//        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.hitListeners.add(blockRemover);
        this.hitListeners.add(ballRemover);
//        this.hitListeners.add(scoreTrackingListener);

        /*               SIDE BLOCKS               */
        Block leftSideBlock = new Block(new Rectangle(
                new Point(0, 0 + SIDE_BLOCKS_TOP_HEIGHT),
                SIDE_BLOCKS_LEFT_WIDTH, SIDE_BLOCKS_LEFT_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR, true);
        Block topSideBlock = new Block(new Rectangle(
                new Point(0, 0),
                SIDE_BLOCKS_TOP_WIDTH, SIDE_BLOCKS_TOP_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR, true);
        Block rightSideBlock = new Block(new Rectangle(
                new Point(SCREEN_WIDTH - SIDE_BLOCKS_RIGHT_WIDTH, SIDE_BLOCKS_TOP_HEIGHT),
                SIDE_BLOCKS_RIGHT_WIDTH, SIDE_BLOCKS_RIGHT_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR, true);
        Block bottomSideBlock = new Block(new Rectangle(
                new Point(SIDE_BLOCKS_LEFT_WIDTH, SCREEN_HEIGHT - SIDE_BLOCKS_BOTTOM_HEIGHT),
                SIDE_BLOCKS_BOTTOM_WIDTH, SIDE_BLOCKS_BOTTOM_HEIGHT),
                SIDE_BLOCKS_FILL_COLOR, true);

        // Death block!
//        bottomSideBlock.addHitListener(ballRemover);

        leftSideBlock.addToGame(this);
        topSideBlock.addToGame(this);
        rightSideBlock.addToGame(this);
        bottomSideBlock.addToGame(this);

        this.scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

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

            // give the ball fixed speed and random direction such that it will also have a nonzero y velocity
            // and move upwards so the player will have a chance to react
            double randomAngle = RandomSingleton.myNextDouble(180, 360);
            gameBall.setVelocity(animations.Velocity.fromAngleAndSpeed(randomAngle, BALL_SPEED));
            gameBall.addToGame(this);
        }

        /*               PADDLE               */
        final int separationFromBottom = 4;
        Rectangle gamePaddleRectangle = new Rectangle(
                new Point(computeAverage(0 + MAIN_BLOCKS_HEIGHT, SCREEN_WIDTH - MAIN_BLOCKS_HEIGHT),
                        SCREEN_HEIGHT - 2 * MAIN_BLOCKS_HEIGHT + separationFromBottom), PADDLE_WIDTH, PADDLE_HEIGHT);
        Paddle gamePaddle = new Paddle(this.gui.getKeyboardSensor(), gamePaddleRectangle);
        gamePaddle.addToGame(this);
    }

    /**
     * Creates a line of blocks and adds them to the game.
     *
     * <p>This method creates a line of blocks starting from a given x-coordinate and at a fixed y-coordinate.
     * The blocks are created with a specified color and are separated by a specified distance.
     * The method continues to create blocks and add them to the game until
     * the x-coordinate of the next block would exceed the width of the game screen.
     *
     * @param separationBetweenBlocks The distance between the blocks.
     *                                This is added to the x-coordinate of each block after it is created.
     * @param startXValue The x-coordinate of the first block to be created.
     * @param blocksYValue The y-coordinate of the blocks. All blocks in the line will have this y-coordinate.
     * @param color The color of the blocks. All blocks in the line will have this color.
     */
    private void createLineOfBlocks(double separationBetweenBlocks, double startXValue,
                                    double blocksYValue, Color color) {
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        while (startXValue < SCREEN_WIDTH - GameSettings.MAIN_BLOCKS_HEIGHT) {
            Block block = new Block(new Rectangle(
                    new Point(startXValue, blocksYValue), MAIN_BLOCKS_WIDTH, GameSettings.MAIN_BLOCKS_HEIGHT), color);
            startXValue += MAIN_BLOCKS_WIDTH + separationBetweenBlocks;
            block.addToGame(this);
            block.addHitListener(this.hitListeners.get(0)); //TODO: CHANGE THIS, THIS MUST BE CHANGED!!!!
            block.addHitListener(scoreTracker);
            this.remainingBlocks.increase(1);
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

            if (this.gameOver) {
                sleeper.sleepFor(3000);
                this.gui.close();
                return;
            }
            // Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (this.remainingBlocks.getValue() == 0) {
                this.scoreIndicator.updateScore(100); // You won!!! +100 points! // TODO: I dont know if there is really a todo here
                this.gameOver = true;
            }
            if (this.remainingBalls.getValue() == 0) {
                this.gameOver = true;
            }
        }
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
