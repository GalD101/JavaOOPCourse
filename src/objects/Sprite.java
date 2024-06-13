// 322558297 Gal Dali

package objects;

import game.Game;
import biuoop.DrawSurface;

/**
 * The Sprite interface represents a graphical object that can be drawn on the screen.
 *
 * <p>Classes that implement the Sprite interface must provide implementations for:
 * drawOn, timePassed, and addToGame methods.
 * The drawOn method is used to draw the Sprite on the screen.
 * The timePassed method is called each time unit to update the state of the Sprite.
 * The addToGame method is used to add the Sprite to a Game,
 * allowing it to be drawn on the screen and to participate in the game's logic.
 */
public interface Sprite {

    /**
     * Draws this Sprite object on the given DrawSurface.
     *
     * <p>This method is part of the Sprite interface and is used to draw the Sprite on the screen.
     * The specific behavior of this method is defined by the classes that implement the Sprite interface.
     * For example, a Ball object might be drawn as a circle, a Paddle object might be drawn as a rectangle, etc.
     *
     * @param d The DrawSurface on which this Sprite is to be drawn
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     *
     * <p>This method is part of the Sprite interface and is called each time unit to update the state of the Sprite.
     * The specific behavior of this method is defined by the classes that implement the Sprite interface.
     * For example, a Paddle object might move left or right, a Ball object might move in a certain direction, etc.
     */
    void timePassed();

    /**
     * Adds this Sprite object to the given Game.
     *
     * <p>This method is used to add the Sprite to a Game,
     * allowing it to be drawn on the screen and to participate in the game's logic.
     * The specific behavior of this method is defined by the classes that implement the Sprite interface.
     *
     * @param game The Game to which this Sprite is to be added
     */
    void addToGame(Game game);
}
