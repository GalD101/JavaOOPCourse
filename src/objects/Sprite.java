package objects;

import game.Game;
import biuoop.DrawSurface;

public interface Sprite {
    // Ball, Block, Paddle, ...  should implement this interface.
    // draw the sprite to the screen
    void drawOn(DrawSurface d);

    // notify the sprite that time has passed
    void timePassed();

    void addToGame(Game game);
}
