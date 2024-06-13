// 322558297 Gal Dali

package utils;

/**
 * This class provides utility methods for comparing and truncating numbers with a certain precision.
 * The precision is defined by the TOLERANCE constant.
 */
public final class Threshold {
    private static final int DECIMALS = 7;
    /**
     * The tolerance used for number comparison and truncation.
     * It is calculated as 10 raised to the power of the negative value of DECIMALS.
     */
    public static final double TOLERANCE = Math.pow(10, -DECIMALS);

    /**
     * Truncates a number to a certain precision using the value of the tolerance.
     *
     * @param number The number to be truncated.
     * @return The truncated number.
     * The number is first multiplied by a scale factor which is the 10
     * raised to the power of the reciprocal of the tolerance.
     * The result is then rounded down to the nearest whole number using the Math.floor method
     * and finally divided by the scale factor to get the truncated number.
     */
    public static double truncateToTolerance(double number) {
        double scale = Math.pow(10, DECIMALS);
        double testing = Math.round(number * scale) / scale;
        return number > 0 ? Math.floor(number * scale) / scale : Math.ceil(number * scale) / scale;
    }

    /**
     * Truncates the absolute difference between two numbers to a certain tolerance
     * and checks if it is less than or equal to the tolerance.
     *
     * @param a The first number to compare.
     * @param b The second number to compare.
     * @return true if the truncated absolute difference between the two numbers is less than or equal to
     * the tolerance defined in the Threshold class, false otherwise.
     * The comparison is done after truncating each number and their absolute difference to a precision
     * defined by the reciprocal of the tolerance using the truncateToTolerance method.
     */
    public static boolean compareNumbers(double a, double b) {
        return truncateToTolerance(Math.abs(a - b)) <= Threshold.TOLERANCE;
    }

    /**
     * Checks if a number is in the range between two other numbers.
     * The comparison is done after truncating each number
     * to a certain precision using the truncateToTolerance method.
     * The range is considered to be inclusive, and a tolerance is added to the range to account for rounding errors.
     *
     * @param val1 The start of the range.
     * @param num  The number to check.
     * @param val2 The end of the range.
     * @return true if the number is in the range, false otherwise.
     */
    public static boolean isInRange(double val1, double num, double val2) {
        return ((val1 - Threshold.TOLERANCE <= num
                && num <= val2 + Threshold.TOLERANCE)
                || (val2 - Threshold.TOLERANCE <= num
                && num <= val1 + Threshold.TOLERANCE));
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Threshold() {
        // restrict instantiation
    }
}