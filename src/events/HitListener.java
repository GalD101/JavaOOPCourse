package events;

import objects.Block;
import objects.Ball;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 * It implements the HitListener interface to listen for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever a block is hit by a ball.
     * The hitter parameter is the Ball that's doing the hitting.
     * It defines the action to be taken when a collision occurs between a ball and a block within the game.
     * Implementing classes should specify the behavior following such an event,
     * typically involving modifying the game's state such as removing the ball,
     * the block, or both, and updating scores or other game metrics.
     *
     * @param beingHit The block that has been hit by the ball.
     * @param hitter   The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
