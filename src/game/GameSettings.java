// 322558297 Gal Dali

package game;

import java.awt.Color;

/**
 * The GameSettings class holds all the settings for the game.
 * This includes settings related to the game mode, screen, ball, paddle, and blocks.
 * Each setting is defined as a public static final variable,
 * making them constants that can be accessed directly via the class name.
 * This class centralizes all the settings and makes them easily accessible throughout the game code.
 */
public class GameSettings {

    /*                              GAME_MODE                           */
    public static final boolean IS_FUN_MODE = true;


    /*                              SCREEN                              */
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    public static final java.awt.Color SCREEN_BACKGROUND_COLOR = Color.DARK_GRAY;


    /*                              BALL                                */
    public static final int BALL_MAX_SIZE = 5;
    public static final int BALL_SIZE = Math.min(BALL_MAX_SIZE, 5);

    public static final double BALL_SPEED = 5;

    public static final Color BALL_FILL_COLOR = Color.WHITE;
    public static final Color BALL_BORDER_COLOR = Color.BLACK;
    public static final Color BALL_CENTER_POINT_COLOR = Color.RED;

    public static final int NUM_OF_BALLS = 2;


    /*                              PADDLE                              */
    public static final double PADDLE_WIDTH = 100;
    public static final double PADDLE_HEIGHT = 0.2 * PADDLE_WIDTH;

    public static final double PADDLE_REGION_ONE = 0.2 * PADDLE_WIDTH;
    public static final double PADDLE_REGION_TWO = 2 * 0.2 * PADDLE_WIDTH;
    public static final double PADDLE_REGION_THREE = 3 * 0.2 * PADDLE_WIDTH;
    public static final double PADDLE_REGION_FOUR = 4 * 0.2 * PADDLE_WIDTH;
    public static final double PADDLE_REGION_FIVE = 5 * 0.2 * PADDLE_WIDTH;

    public static final double PADDLE_SPEED = 5;

    public static final Color PADDLE_FILL_COLOR = Color.YELLOW;
    public static final Color PADDLE_BORDER_COLOR = Color.BLACK;


    /*                              BLOCKS                              */
    public static final Color BLOCKS_BORDER_COLOR = Color.BLACK;

    /*                              MAIN_BLOCKS                         */
    public static final double MAIN_BLOCKS_WIDTH = 50;
    public static final double MAIN_BLOCKS_HEIGHT = 0.5 * MAIN_BLOCKS_WIDTH;

    public static final Color[] MAIN_BLOCKS_FILL_COLOR =
            {Color.RED, Color.CYAN, Color.GREEN, Color.MAGENTA,
                    Color.ORANGE, Color.BLUE, Color.PINK, Color.WHITE, Color.YELLOW};

    /*                              SIDE_BLOCKS                         */
    public static final double SIDE_BLOCKS_TOP_WIDTH = SCREEN_WIDTH;
    public static final double SIDE_BLOCKS_TOP_HEIGHT = 25;

    public static final double SIDE_BLOCKS_LEFT_WIDTH = 25;
    public static final double SIDE_BLOCKS_LEFT_HEIGHT = SCREEN_HEIGHT - SIDE_BLOCKS_TOP_HEIGHT;

    public static final double SIDE_BLOCKS_RIGHT_WIDTH = 25;
    public static final double SIDE_BLOCKS_RIGHT_HEIGHT = SCREEN_HEIGHT - SIDE_BLOCKS_TOP_HEIGHT;

    public static final double SIDE_BLOCKS_BOTTOM_WIDTH =
            SCREEN_WIDTH - SIDE_BLOCKS_LEFT_WIDTH - SIDE_BLOCKS_RIGHT_WIDTH;
    public static final double SIDE_BLOCKS_BOTTOM_HEIGHT = 25;

    public static final Color SIDE_BLOCKS_FILL_COLOR = Color.GRAY;
}
