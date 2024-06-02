// 322558297 Gal Dali
package utils;

/**
 * This class provides a utility method for converting a string to an integer.
 * The method handles cases where the string starts with a "+" sign or contains non-numeric characters.
 * If the string starts with a "+" sign, the sign is removed before the conversion.
 * If the string starts with a "-" sign after removing the "+" sign,
 * or if it contains non-numeric characters, the method returns 0.
 * If the string represents a number that is too large to be represented as an integer, the method also returns 0.
 */
public class InputValidator {
    /**
     * Converts a string to an integer.
     * Handles cases where the string starts with a "+" sign or contains non-numeric characters.
     * If the string starts with a "+" sign, the sign is removed before the conversion.
     * If the string starts with a "-" sign after removing the "+" sign,
     * or if it contains non-numeric characters, the method returns 0.
     * If the string represents a number that is too large to be represented as an integer, the method also returns 0.
     *
     * @param input The string to be converted to an integer.
     * @return The integer representation of the string, or 0 if the string cannot be converted to an integer.
     */
    public static int stringToInteger(String input) {
        while (input.startsWith("+")) {
            input = input.substring(1);
        }
        if (input.matches("^-?\\d+$")) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * Converts a string to an integer.
     * Handles cases where the string starts with a "+" sign or contains non-numeric characters.
     * If the string starts with a "+" sign, the sign is removed before the conversion.
     * If the string starts with a "-" sign after removing the "+" sign,
     * or if it contains non-numeric characters, the method returns 0.
     * If the string represents a number that is too large to be represented as an integer, the method also returns 0.
     *
     * @param input The string to be converted to an integer.
     * @return The integer representation of the string, or 0 if the string cannot be converted to an integer.
     */
    public static int stringToInteger2(String input) {
        while (input.startsWith("+")) {
            input = input.substring(1);
            if (input.startsWith("-")) {
                return -1;
            }
        }
        if (input.matches("^\\d+$")) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return -1;
            }
        } else {
            return -1;
        }
    }
}