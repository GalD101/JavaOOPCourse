package events;

import game.Game;
import objects.Ball;
import objects.Block;
import utils.Counter;

public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover object.
     *
     * <p>This constructor creates a new BallRemover object with a specified game and counter.
     * The game is directly assigned.
     * The counter is directly assigned. //TODO: Maybe make a copy of the counter?
     *
     * @param game           The game to remove balls from. Must not be null.
     * @param remainingBalls  The counter to decrement when a ball is removed.
     * @throws IllegalArgumentException if game is null.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        if (game == null) {
            throw new IllegalArgumentException("The game must not be null.");
        }
        if (remainingBalls == null) {
            throw new IllegalArgumentException("The counter must not be null.");
        }
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
