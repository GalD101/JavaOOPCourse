package events;

import game.Game;
import objects.Ball;
import objects.Block;
import utils.Counter;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 * It implements the HitListener interface to listen for hit events.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover object.
     *
     * <p>This constructor creates a new BallRemover object with a specified game and counter.
     * The game is directly assigned.
     * The counter is directly assigned.
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
