package utils;

public class InputValidator {
    public static int stringToInteger(String input) {
        while (input.startsWith("+")) {
            input = input.substring(1);
        }
        if (input.matches("^\\d+")) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }
}
