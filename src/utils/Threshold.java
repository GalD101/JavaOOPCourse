package utils;

public final class Threshold {
    private static final int DECIMALS = 7;
    public static final double TOLERANCE = Math.pow(10, -DECIMALS);

    /**
     * Truncates a number to a certain precision using the value of the tolerance.
     *
     * @param number The number to be truncated.
     * @return The truncated number. The number is first multiplied by a scale factor which is the 10 raised to the power of the reciprocal of the tolerance.
     * The result is then rounded down to the nearest whole number using the Math.floor method and finally divided by the scale factor to get the truncated number.
     */
    public static double truncateToTolerance(double number) {
        double scale = Math.pow(10, DECIMALS);
        // NOTE: This function will not work for huge numbers (~10^300)
        // FIXME: Think what to do when numbers are too big (!!!)
        // There will always be issues with this method.
        // This is due to the fact that the computer simply represents numbers in a different way, and thus there will always be some error.
        // The best way to handle big numbers is to use BigDecimal, however, I will simply assume our project won't have to deal with such big numbers.

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

    private Threshold() {
        // restrict instantiation
    }
}