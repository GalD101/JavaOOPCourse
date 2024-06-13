// 322558297 Gal Dali

package utils;

import java.util.Random;

/**
 * This class provides a singleton instance of the Random class.
 * The singleton pattern ensures that there is only one instance of Random in the application that can be used globally.
 * This is useful when a single instance of a class needs to coordinate actions across the system.
 */
public final class RandomSingleton {
    /**
     * The single instance of the Random class.
     * It is declared as final to ensure that it cannot be reinitialized.
     */
    private static final Random INSTANCE = new Random();

    /**
     * Private constructor to prevent instantiation of this class.
     * This is a key characteristic of the singleton pattern.
     */
    private RandomSingleton() {
        // private constructor to prevent instantiation
    }

    /**
     * Provides global access to the single instance of the Random class.
     *
     * @return The single instance of the Random class.
     */
    public static Random getInstance() {
        return INSTANCE;
    }

    /**
     * Generates a random double value between the origin and the bound.
     * This is used since the Random class in some versions of java
     * does not provide a method for generating random double values in a specific range.
     *
     * @param origin The lower bound of the random double value.
     * @param bound  The upper bound of the random double value.
     * @return A random double value between the origin and the bound.
     */
    public static double myNextDouble(double origin, double bound) {
        return origin + (bound - origin) * INSTANCE.nextDouble();
    }
}