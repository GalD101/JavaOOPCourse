package game;

import java.awt.Color;

public class GameSettings {
    /*                              SCREEN                              */
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final java.awt.Color SCREEN_BACKGROUND_COLOR = Color.BLACK;

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
    public static final double PADDLE_SPEED = 5;
    public static final Color PADDLE_FILL_COLOR = Color.YELLOW;
    public static final Color PADDLE_BORDER_COLOR = Color.BLACK;

    /*                              BLOCKS                              */
    public static final Color BLOCKS_BORDER_COLOR = Color.BLACK;

    /*                              MAIN_BLOCKS                         */
    public static final double MAIN_BLOCKS_WIDTH = 50;
    public static final double MAIN_BLOCKS_HEIGHT = 0.5 * MAIN_BLOCKS_WIDTH;
    //    public static final boolean MAIN_BLOCKS_IS_RANDOM_COLOR = false;
    public static final Color[] MAIN_BLOCKS_FILL_COLOR = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};


    // side blocks are just like side chicks
    // TODO: Remove stupid comment

    /*                              SIDE_BLOCKS                         */
    public static final double SIDE_BLOCKS_TOP_WIDTH = SCREEN_WIDTH;
    public static final double SIDE_BLOCKS_TOP_HEIGHT = 25;

    public static final double SIDE_BLOCKS_LEFT_WIDTH = 25;
    public static final double SIDE_BLOCKS_LEFT_HEIGHT = SCREEN_HEIGHT - SIDE_BLOCKS_TOP_HEIGHT;

    public static final double SIDE_BLOCKS_RIGHT_WIDTH = 25;
    public static final double SIDE_BLOCKS_RIGHT_HEIGHT = SCREEN_HEIGHT - SIDE_BLOCKS_TOP_HEIGHT;

    public static final double SIDE_BLOCKS_BOTTOM_WIDTH = SCREEN_WIDTH - SIDE_BLOCKS_LEFT_WIDTH - SIDE_BLOCKS_RIGHT_WIDTH;
    public static final double SIDE_BLOCKS_BOTTOM_HEIGHT = 25;

    public static final Color SIDE_BLOCKS_FILL_COLOR = Color.GRAY;

}
