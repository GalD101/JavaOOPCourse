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
        // FIXME: Think what to do when numbers are too big (!!!)
        // When the number is too big (for example Double.MAX_VALUE),
        // there are weird issues here.
        // TODO: Maybe add if condition to check if number is small or big
        if (number > Double.MAX_VALUE / scale) {
            return Double.MAX_VALUE;
        }
        double testing = Math.round(number * scale) / scale;
        return Math.round(number * scale) / scale;
    }

    private Threshold() {
        // restrict instantiation
    }
}