package events;

import objects.Ball;
import objects.Block;
import utils.Counter;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener object.
     *
     * <p>This constructor creates a new ScoreTrackingListener object with a specified counter.
     * The counter is directly assigned. //TODO: Maybe make a copy of the counter?
     *
     * @param scoreCounter The counter to increment when a hit occurs. Must not be null.
     * @throws IllegalArgumentException if scoreCounter is null.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        if (scoreCounter == null) {
            throw new IllegalArgumentException("The counter must not be null.");
        }
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
