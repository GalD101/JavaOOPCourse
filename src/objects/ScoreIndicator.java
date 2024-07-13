package objects;

import biuoop.DrawSurface;
import utils.Counter;
import game.Game;

/**
 * Represents the score indicator in the game.
 * This class is responsible for displaying the current score in the game. It implements the Sprite interface,
 * allowing it to be drawn on the game's draw surface. The score is managed using a Counter object, which
 * tracks the numerical value of the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a new ScoreIndicator object.
     *
     * <p>This constructor creates a new ScoreIndicator object with a specified counter.
     * The counter is directly assigned.
     *
     * @param scoreCounter The counter to display. Must not be null.
     * @throws IllegalArgumentException if scoreCounter is null.
     */
    public ScoreIndicator(Counter scoreCounter) {
        if (scoreCounter == null) {
            throw new IllegalArgumentException("The counter must not be null.");
        }
        this.score = scoreCounter;
    }

    /**
     * Updates the score by a specified number of points.
     * This method increases the current score by the given amount. It is typically called
     * when the player achieves a score-worthy event, such as destroying a block or completing a level.
     *
     * @param points The number of points to add to the current score. Can be positive or negative.
     */
    public void updateScore(int points) {
        this.score.increase(points);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.WHITE);
        d.drawText(350, 15, "Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {
        // Do nothing.
    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
