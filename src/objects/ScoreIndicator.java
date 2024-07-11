package objects;

import biuoop.DrawSurface;
import utils.Counter;
import game.Game;

public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a new ScoreIndicator object.
     *
     * <p>This constructor creates a new ScoreIndicator object with a specified counter.
     * The counter is directly assigned. //TODO: Maybe make a copy of the counter?
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
