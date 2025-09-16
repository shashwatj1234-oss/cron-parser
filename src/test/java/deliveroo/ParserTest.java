package deliveroo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parserTest() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-30 * 1-5 /usr/bin/find";
        String output = parser.parse(input);

        String[] namesTimesPairs = output.split("\n");
        assertEquals(6, namesTimesPairs.length, "Should produce 6 lines");

        String[] minutesDetails = namesTimesPairs[0].trim().split("\\s+");
        assertEquals(5, minutesDetails.length, "Minute line should have label + 4 values");

        for (int i = 0; i < 4; i++) {
            assertEquals(String.valueOf(i * 15), minutesDetails[i + 1]);
        }
    }

    @Test
    void parserFailedTest() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-30 * 1-5"; // only 5 fields instead of 6
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void parserFailedWrongFormattedTest() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-a * 1-5 aa"; // 'a' is not a number
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void parserFailedWrongRangeTest() {
        Parser parser = new Parser();
        String input = "*/15 0 40 * 1-5 aa"; // 40 is out of valid range
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void wildcardExpandsCorrectly() {
        Parser parser = new Parser();
        String input = "* * * * * echo";
        String output = parser.parse(input);

        // Minutes should expand to 0..59
        String[] minutesLine = output.split("\n")[0].trim().split("\\s+");
        assertEquals(61, minutesLine.length, "Should expand to 60 minutes + label");
        assertEquals("0", minutesLine[1]);
        assertEquals("59", minutesLine[60]);
    }

    @Test
    void rangeValidatesMonth() {
        Parser parser = new Parser();
        String input = "0 0 1 11-2 * cmd"; // invalid: month 11-2 is decreasing
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void wrapAroundWeekRange() {
        Parser parser = new Parser();
        String input = "0 0 * * 5-2 ls"; // wrap-around days of week
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void stepOverRange() {
        Parser parser = new Parser();
        String input = "5-15/5 * * * * run";
        String output = parser.parse(input);

        String[] minutesLine = output.split("\n")[0].trim().split("\\s+");
        assertEquals("5", minutesLine[1]);
        assertEquals("10", minutesLine[2]);
        assertEquals("15", minutesLine[3]);
    }

    @Test
    void invalidStepZero() {
        Parser parser = new Parser();
        String input = "*/0 * * * * job";
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void singleValueValid() {
        Parser parser = new Parser();
        String input = "7 12 * * * run";
        String output = parser.parse(input);

        String[] minutesLine = output.split("\n")[0].trim().split("\\s+");
        assertEquals("7", minutesLine[1]);

        String[] hoursLine = output.split("\n")[1].trim().split("\\s+");
        assertEquals("12", hoursLine[1]);
    }

    @Test
    void parserTest2() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-30 JAN 1-5 /usr/bin/find";
        String output = parser.parse(input);

        String[] namesTimesPairs = output.split("\n");
        assertEquals(6, namesTimesPairs.length, "Should produce 6 lines");

        String[] monthDetails = namesTimesPairs[3].trim().split("\\s+");
        assertEquals(1, Integer.valueOf(monthDetails[1]));
    }
}
