package utils;

public class InputValidator {
    public static int stringToInteger(String input) {
        while (input.startsWith("+")) {
            input = input.substring(1);
            if (input.startsWith("-")) {
                return 0;
            }
        }
        if (input.matches("^\\d+$")) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
