package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidatorTest {

    @Test
    public void stringToInteger_shouldReturnInteger_whenInputIsPositiveNumber() {
        String input = "+123";
        int result = InputValidator.stringToInteger(input);
        assertEquals(123, result);
    }

    @Test
    public void stringToInteger_shouldReturnInteger_whenInputIsNumber() {
        String input = "456";
        int result = InputValidator.stringToInteger(input);
        assertEquals(456, result);
    }

    @Test
    public void stringToInteger_shouldReturnZero_whenInputIsNotNumber() {
        String input = "abc";
        int result = InputValidator.stringToInteger(input);
        assertEquals(0, result);
    }

    @Test
    public void stringToInteger_shouldReturnZero_whenInputIsEmpty() {
        String input = "";
        int result = InputValidator.stringToInteger(input);
        assertEquals(0, result);
    }
}