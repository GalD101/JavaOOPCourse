// 322558297 Gal Dali

package utils;

/**
 * Represents the score indicator in the game.
 * This class is responsible for displaying the current score in the game. It implements the Sprite interface,
 * allowing it to be drawn on the game's draw surface. The score is managed using a Counter object, which
 * tracks the numerical value of the score.
 */
public class Counter {
    private int value;

    /**
     * Default constructor for Counter class.
     * Initializes a new Counter instance with the value set to 0. This constructor is used
     * to create a counter starting from zero, suitable for scenarios where the initial count
     * should begin at the default state.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Constructs a Counter instance with a specified initial value.
     * This constructor allows for the creation of a counter with a predefined value,
     * enabling the counter to start from a value other than zero. This can be useful
     * in scenarios where the initial count needs to reflect a certain state or quantity
     * that is not the default.
     *
     * @param value The initial value of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases the current value of the counter by a specified number.
     * This method adds the given number to the current count, effectively increasing the counter's value.
     * It can be utilized to increment the counter in response to various game events or operations that require
     * adding a count, such as gaining points or increasing a quantity of items.
     *
     * @param number The number to add to the current count.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the current value of the counter by a specified number.
     * This method subtracts the given number from the current count, effectively decreasing the counter's value.
     * It can be used to decrement the counter in response to various game events or operations that require
     * reducing a count, such as losing points or decreasing a quantity of items.
     *
     * @param number The number to subtract from the current count.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Retrieves the current value of the counter.
     * This method returns the current count held by the Counter instance, allowing access to its value
     * at any point in time. It is useful for obtaining the current state of the counter for display,
     * calculations, or other purposes where the current count is needed.
     *
     * @return the current count as an integer.
     */
    public int getValue() {
        return this.value;
    }
}
