package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThresholdTest {

    @Test
    public void truncateToTolerance_shouldReturnSameNumber_whenNumberIsTooBig() {
        double bigNumber = Double.MAX_VALUE;
        double result = Threshold.truncateToTolerance(bigNumber);
        assertEquals(bigNumber, result);
    }

    @Test
    public void truncateToTolerance_shouldReturnTruncatedNumber_whenNumberIsSmall() {
        double smallNumber = 0.123456789;
        double result = Threshold.truncateToTolerance(smallNumber);
        assertEquals(0.1234567, result);
    }

    @Test
    public void truncateToTolerance_shouldReturnZero_whenNumberIsZero() {
        double zeroNumber = 0.0;
        double result = Threshold.truncateToTolerance(zeroNumber);
        assertEquals(0.0, result);
    }

    @Test
    public void truncateToTolerance_shouldReturnNegativeTruncatedNumber_whenNumberIsNegative() {
        double negativeNumber = -0.123456789;
        double result = Threshold.truncateToTolerance(negativeNumber);
        assertEquals(-0.1234567, result);
    }
}