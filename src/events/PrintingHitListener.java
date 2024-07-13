package events;

import objects.Ball;
import objects.Block;

/**
 * The HitNotifier interface defines the contract for objects that can register and remove HitListeners.
 * Objects implementing this interface can notify registered listeners about specific hit events,
 * such as a collision in a game. This mechanism allows for a flexible and dynamic way of handling
 * game events between different components.
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
