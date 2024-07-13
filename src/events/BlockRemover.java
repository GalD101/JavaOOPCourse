package events;

import game.Game;
import objects.Ball;
import objects.Block;
import utils.Counter;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 * It implements the HitListener interface to listen for hit events.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover object.
     *
     * <p>This constructor creates a new BlockRemover object with a specified game and counter.
     * The game is directly assigned.
     * The counter is directly assigned.
     *
     * @param game            The game to remove blocks from. Must not be null.
     * @param remainingBlocks The counter to decrement when a block is removed.
     * @throws IllegalArgumentException if game is null.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        if (game == null) {
            throw new IllegalArgumentException("The game must not be null.");
        }
        if (remainingBlocks == null) {
            throw new IllegalArgumentException("The counter must not be null.");
        }
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
