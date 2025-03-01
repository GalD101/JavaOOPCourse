// 322558297 Gal Dali

package utils;

/**
 * This class provides utility methods for mathematical operations.
 */
public final class MathUtils {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MathUtils() {
        // private constructor to prevent instantiation
    }

    /**
     * Calculates the average of two numbers in a way that prevents overflow.
     * If a > b, calculates (a - b) / 2 + b.
     * If a < b, calculates (b - a) / 2 + a.
     * If a = b, returns a.
     * If a and b have different signs, calculates (a + b) / 2.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The average of the two numbers.
     */
    public static double computeAverage(double a, double b) {
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            // Numbers have different signs,
            // therefore, (a + b) / 2 will prevent overflow.
            return (a + b) / 2;
        }
        // Numbers have the same sign,
        // therefore, (a - b) / 2 + b will prevent overflow.
        // Use the following formula: assume (without lose of generality) that a > b so that means:
        // (a - b) / 2 + b = (a - b + 2*b) / 2 = (a + b) / 2 === midPoint!
        // This formula will prevent overflow.
        if (a >= b) {
            return ((a - b) / 2) + b;
        }
        return ((b - a) / 2) + a;
    }
}