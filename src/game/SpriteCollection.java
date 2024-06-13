package game;

import biuoop.DrawSurface;
import objects.Sprite;

/**
 * The SpriteCollection class represents a collection of Sprite objects in the game.
 *
 * <p>This class maintains a list of Sprite objects that can be drawn on the screen and updated each time unit.
 * It provides methods to add a sprite to the collection,
 * update the state of all sprites, and draw all sprites on a given DrawSurface.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spriteList;

    /**
     * Default constructor for the SpriteCollection class.
     *
     * <p>This constructor initializes a new SpriteCollection object with an empty ArrayList of Sprite objects.
     * The ArrayList is used to store and manage all the Sprite objects in the game, such as the Ball and the Paddle.
     */
    public SpriteCollection() {
        this.spriteList = new java.util.ArrayList<Sprite>();
    }

    /**
     * Adds a Sprite object to the spriteList.
     *
     * <p>This method takes a Sprite object as an argument and adds it to the spriteList.
     * If the provided Sprite object is null, the method simply returns without making any changes.
     *
     * @param s The Sprite object to be added to the spriteList
     */
    public void addSprite(Sprite s) {
        if (s == null) {
            return;
        }
        this.spriteList.add(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the spriteList.
     *
     * <p>This method iterates over all the Sprite objects in the spriteList and calls their timePassed method.
     * This is typically used to update the state of each Sprite for the next frame of the game's animation.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : this.spriteList) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites on the provided DrawSurface.
     *
     * <p>This method iterates over all the Sprite objects in the spriteList and calls their drawOn method,
     * passing the provided DrawSurface as an argument. This causes each Sprite to be drawn on the DrawSurface.
     *
     * @param d The DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.spriteList) {
            s.drawOn(d);
        }
    }
}
