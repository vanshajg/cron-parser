package com.vanshajgirotra.cron.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CronParserTest {

    final static CronParser cronParser = new CronParser();

    @Test
    void testParser() {
        final List<CronParser.ParsedData> parsedData = List.of(
                createParsedData("minute", "0 15 30 45"),
                createParsedData("hour", "0"),
                createParsedData("day of month", "1 15"),
                createParsedData("month", "*"),
                createParsedData("day of week", "1 2 3 4 5"),
                createParsedData("command", "/usr/bin/find")
        );
        assertEquals(parsedData, cronParser.parse("*/15 0 1,15 * 1-5 /usr/bin/find"));
    }

    @Test
    void testPrettyPrint() {

        final List<CronParser.ParsedData> parsedData = List.of(
                createParsedData("minute", "0 15 30 45"),
                createParsedData("hour", "0"),
                createParsedData("day of month", "1 15"),
                createParsedData("month", "*"),
                createParsedData("day of week", "1 2 3 4 5"),
                createParsedData("command", "/usr/bin/find")
        );
        final String expected = """
                minute          0 15 30 45
                hour            0
                day of month    1 15
                month           *
                day of week     1 2 3 4 5
                command         /usr/bin/find""";
        assertEquals(expected, CronParser.prettyFormat(parsedData));
    }

    @Test
    void parserThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cronParser.parse("*/15 0 1,15 * 1-5"));
    }


    private CronParser.ParsedData createParsedData(String parserName, String parsedValue) {
        return new CronParser.ParsedData(parserName, parsedValue);
    }

}