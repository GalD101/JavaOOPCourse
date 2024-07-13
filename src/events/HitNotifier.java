// 322558297 Gal Dali

package events;

/**
 * The HitNotifier interface defines the contract for objects that can register and remove HitListeners.
 * Objects implementing this interface can notify registered listeners about specific hit events,
 * such as a collision in a game. This mechanism allows for a flexible and dynamic way of handling
 * game events between different components.
 */
public interface HitNotifier {
    /**
     * Registers a HitListener to be notified about hit events.
     * This method allows objects interested in hit events to listen for them,
     * enabling the game to notify these objects whenever a hit event occurs.
     *
     * @param hl The HitListener to be added to the list of listeners. Must not be null.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a specified HitListener from the list of listeners that are notified about hit events.
     * This method allows for dynamic changes in the list of objects that should be notified when a hit event occurs,
     * enabling the addition or removal of game elements that interact with hit events during runtime.
     *
     * @param hl The HitListener to be removed from the list of listeners. Must not be null.
     */
    void removeHitListener(HitListener hl);
}
